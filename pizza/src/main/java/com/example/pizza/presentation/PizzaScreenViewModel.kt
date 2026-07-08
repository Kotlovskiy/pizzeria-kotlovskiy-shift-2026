package com.example.pizza.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.errors.NoInternetException
import com.example.errors.ServerErrorException
import com.example.pizza.domain.Dough
import com.example.pizza.domain.Ingredient
import com.example.pizza.domain.Pizza
import com.example.pizza.domain.PizzaRepository
import com.example.pizza.domain.Size
import com.example.pizza.presentation.PizzaScreenState.Initial
import com.example.pizza.presentation.PizzaScreenState.LoadingContent
import com.example.pizza.presentation.PizzaScreenState.Navigation
import com.example.pizza.presentation.PizzaScreenState.Content
import com.example.pizza.presentation.PizzaScreenState.Error
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
import kotlin.collections.filter
import kotlin.collections.indexOfFirst
import kotlin.collections.toSet

@HiltViewModel(assistedFactory = PizzaScreenViewModel.Factory::class)
class PizzaScreenViewModel @AssistedInject constructor(
    @Assisted private val pizzaDestination: PizzaDestination,
    private val repository: PizzaRepository
) : ViewModel() {
    private val _state = MutableStateFlow<PizzaScreenState>(Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is NoInternetException -> _state.update {
                Error(PizzaScreenError.NoInternet)
            }
            is ServerErrorException -> _state.update {
                Error(PizzaScreenError.ServerError)
            }
            else -> _state.update { Error(PizzaScreenError.SmtWentWrong) }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { LoadingContent }
            val result = repository.getPizzaInfoById(pizzaDestination.pizzaId)
            _state.update {
                Content(
                    pizzaInfo = result,
                    allToppings = result.toppings,
                    selectedDough = pizzaDestination.pizza?.dough ?: result.doughs.first {
                        it.type == Dough.THIN
                    },
                    selectedSize = pizzaDestination.pizza?.size ?: result.sizes.first {
                        it.size == Size.SMALL
                    },
                    selectedToppings = pizzaDestination.pizza?.toppings?.map { it.type } ?: listOf(),
                    isEditMode = pizzaDestination.pizza != null
                )
            }
        }
    }

    fun updateSize(size: Size) {
        if (_state.value !is Content) return
        _state.update {
            (it as Content).copy(
                selectedSize = it.pizzaInfo.sizes.first { sizeInfo ->
                    sizeInfo.size == size
                }
            )
        }
    }

    fun updateToppings(ingredient: Ingredient) {
        if (_state.value !is Content) return
        _state.update {
            (it as Content).copy(
                selectedToppings =
                    if(inToppings(it.selectedToppings, ingredient)) {
                        it.selectedToppings.filter { item -> item != ingredient }
                    } else {
                        it.selectedToppings + ingredient
                    }
            )
        }
    }

    fun clickAddUpdateButton() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            val currentState = _state.value as? Content ?: return@launch

            _state.update { (it as Content).copy(loading = true) }

            if (pizzaDestination.pizza == null) {
                addPizza(currentState)
            } else {
                updatePizza(currentState, pizzaDestination.pizza)
            }

            _state.update { Navigation }
        }
    }

    private suspend fun addPizza(currentState: Content) {
        repository.addPizza(
            Pizza(
                id = currentState.pizzaInfo.id,
                image = currentState.pizzaInfo.image,
                title = currentState.pizzaInfo.title,
                size = currentState.selectedSize,
                dough = currentState.selectedDough,
                toppings = currentState.allToppings.filter {
                    it.type in currentState.selectedToppings
                }
            )
        )
    }

    private suspend fun updatePizza(
        currentState: Content,
        pizza: Pizza
    ) {
        repository.updatePizza(
            pizza = pizza,
            size = currentState.selectedSize.takeIf {
                pizza.size.size != it.size
            },
            dough = currentState.selectedDough.takeIf {
                pizza.dough.type != it.type
            },
            toppings = pizza.toppings
                .filter { it.type in currentState.selectedToppings }
                .takeIf {
                    it.toSet() != pizza.toppings.toSet()
                }
        )
    }

    private fun inToppings(
        selectedToppings: List<Ingredient>,
        ingredient: Ingredient
    ): Boolean {
        return selectedToppings.indexOfFirst { item -> item == ingredient  } == -1
    }

    @AssistedFactory
    interface Factory {
        fun create(pizzaDestination: PizzaDestination): PizzaScreenViewModel
    }
}
