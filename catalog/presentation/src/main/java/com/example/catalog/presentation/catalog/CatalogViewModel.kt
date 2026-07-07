package com.example.catalog.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catalog.presentation.catalog.CatalogScreenError.NoInternet
import com.example.catalog.presentation.catalog.CatalogScreenError.ServerError
import com.example.catalog.presentation.catalog.CatalogScreenError.SmtWentWrong
import com.example.catalog.presentation.catalog.CatalogScreenState.Empty
import com.example.catalog.presentation.catalog.CatalogScreenState.Error
import com.example.catalog.presentation.catalog.CatalogScreenState.Initial
import com.example.catalog.presentation.catalog.CatalogScreenState.Loading
import com.example.catalog.presentation.catalog.CatalogScreenState.Success
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
    private val _state = MutableStateFlow<CatalogScreenState>(Initial)
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
            _state.update { Loading }
            val pizzas = repository.getPizzas()
            _state.update {
                if(pizzas.isNotEmpty())
                    Success(pizzas)
                else
                    Empty
            }
        }
    }
}
