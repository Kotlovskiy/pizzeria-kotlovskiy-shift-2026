package com.example.catalog.presentation.pizza

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.presentation.catalog.CatalogScreenError.NoInternet
import com.example.catalog.presentation.catalog.CatalogScreenError.ServerError
import com.example.catalog.presentation.catalog.CatalogScreenError.SmtWentWrong
import com.example.catalog.presentation.pizza.PizzaScreenState.Error
import com.example.catalog.presentation.pizza.PizzaScreenState.Initial
import com.example.catalog.presentation.pizza.PizzaScreenState.LoadingContent
import com.example.catalog.presentation.pizza.PizzaScreenState.Navigation
import com.example.catalog.presentation.pizza.PizzaScreenState.Success
import com.example.domain.Dough
import com.example.domain.ICatalogRepository
import com.example.domain.Ingredient
import com.example.domain.Pizza
import com.example.domain.Size
import com.example.errors.NoInternetException
import com.example.errors.ServerErrorException
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = PizzaScreenViewModel.Factory::class)
class PizzaScreenViewModel @AssistedInject constructor(
    @Assisted private val pizzaDestination: PizzaDestination,
    private val repository: ICatalogRepository
) : ViewModel() {
    private val id: String = pizzaDestination.pizzaId
    private val pizza: Pizza? = pizzaDestination.pizza
    private val _state = MutableStateFlow<PizzaScreenState>(Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is NoInternetException -> _state.update {
                Error(NoInternet)
            }
            is ServerErrorException -> _state.update {
                Error(ServerError)
            }
            else -> _state.update { Error(SmtWentWrong) }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { LoadingContent }
            val result = repository.getPizzaInfoById(id)
            _state.update {
                Success(
                    pizzaInfo = result,
                    allToppings = result.toppings,
                    selectedDough = pizza?.dough ?: result.doughs.first { it.type == Dough.THIN },
                    selectedSize = pizza?.size ?: result.sizes.first { it.size == Size.SMALL },
                    selectedToppings = pizza?.toppings?.map { it.type } ?: listOf(),
                    isEditMode = pizza != null
                )
            }
        }
    }

    fun updateSize(size: Size) {
        try {
            _state.update {
                (it as? Success)?.copy(
                    selectedSize = it.pizzaInfo.sizes.first { sizeInfo ->
                        sizeInfo.size == size
                    }
                ) ?: it
            }
        } catch (_: Exception) { }
    }

    fun updateToppings(ingredient: Ingredient) {
        _state.update {
            (it as? Success)?.copy(
                selectedToppings =
                    if(it.selectedToppings.indexOfFirst { item -> item == ingredient  } == -1) {
                        it.selectedToppings + ingredient
                    } else {
                        it.selectedToppings.filter { item -> item != ingredient }
                    }
            ) ?: it
        }
    }

    fun clickButton() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { (it as? Success)?.copy(loading = true) ?: it }
            (_state.value as? Success)?.let { currentState ->
                if (pizza == null) {
                    repository.addPizza(
                        Pizza(
                            id = currentState.pizzaInfo.id,
                            image = currentState.pizzaInfo.image,
                            title = currentState.pizzaInfo.title,
                            size = currentState.selectedSize,
                            dough = currentState.selectedDough,
                            toppings = currentState.allToppings.filter {
                                it.type in currentState.selectedToppings
                            },
                            count = 1
                        )
                    )
                } else {
                    repository.updatePizza(
                        pizza = pizza,
                        size = if (pizza.size.size != currentState.selectedSize.size)
                            currentState.selectedSize
                        else null,
                        dough = if (pizza.dough.type != currentState.selectedDough.type)
                            currentState.selectedDough
                        else null,
                        toppings = if (
                            pizza.toppings.map { it.type }.toSet() !=
                            currentState.selectedToppings.toSet()
                        )
                            pizza.toppings.filter { it.type in currentState.selectedToppings }
                        else null
                    )
                }

                _state.update { Navigation }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(pizzaDestination: PizzaDestination): PizzaScreenViewModel
    }
}
