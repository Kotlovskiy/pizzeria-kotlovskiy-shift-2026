package com.example.catalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ICatalogRepository
import com.example.errors.NoInternetException
import com.example.errors.ServerErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: ICatalogRepository
): ViewModel() {
    private val _state = MutableStateFlow<CatalogScreenState>(CatalogScreenState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is NoInternetException -> _state.update {
                CatalogScreenState.Error(CatalogScreenError.NoInternet)
            }
            is ServerErrorException -> _state.update {
                CatalogScreenState.Error(CatalogScreenError.ServerError)
            }
            else -> _state.update { CatalogScreenState.Error(CatalogScreenError.SmtWentWrong) }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _state.update { CatalogScreenState.Loading }
            val pizzas = repository.getPizzas()
            _state.update {
                if(pizzas.isNotEmpty())
                    CatalogScreenState.Success(pizzas)
                else
                    CatalogScreenState.Empty
            }
        }
    }
}
