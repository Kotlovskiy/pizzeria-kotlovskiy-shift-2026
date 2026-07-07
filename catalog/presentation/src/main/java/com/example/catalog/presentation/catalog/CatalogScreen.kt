package com.example.catalog.presentation.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.catalog.presentation.catalog.CatalogScreenState.Success
import com.example.catalog.presentation.LoadingContent
import com.example.catalog.presentation.catalog.CatalogScreenError.NoInternet
import com.example.catalog.presentation.catalog.CatalogScreenError.ServerError
import com.example.catalog.presentation.catalog.CatalogScreenError.SmtWentWrong
import com.example.catalog.presentation.catalog.CatalogScreenState.Empty
import com.example.catalog.presentation.catalog.CatalogScreenState.Error
import com.example.catalog.presentation.catalog.CatalogScreenState.Initial
import com.example.catalog.presentation.catalog.CatalogScreenState.Loading
import com.example.ui.errorContent.NoInternetContent
import com.example.ui.R.string.check_internet_and_retry
import com.example.ui.R.string.smt_went_wrong
import com.example.ui.R.string.server_not_available
import com.example.ui.R.string.try_again
import com.example.ui.errorContent.ServerErrorContent
import com.example.ui.errorContent.SmtWentWrongContent

@Composable
fun CatalogScreen(
    toPizzaScreen: (id: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    when (state) {
        Initial -> viewModel.loadData()
        Empty -> {}
        is Error -> {
            when ((state as Error).error) {
                NoInternet -> NoInternetContent(
                    text = stringResource(check_internet_and_retry),
                    buttonText = stringResource(try_again),
                    onClickRetry = { viewModel.loadData() },
                    modifier = modifier.padding(horizontal = 32.dp)
                )
                ServerError -> ServerErrorContent(
                    stringResource(server_not_available),
                    modifier = modifier.padding(horizontal = 32.dp)
                )
                SmtWentWrong -> SmtWentWrongContent(
                    text = stringResource(smt_went_wrong),
                    buttonText = stringResource(try_again),
                    onClickRetry = { viewModel.loadData() },
                    modifier = modifier.padding(horizontal = 32.dp)
                )
            }
        }
        Loading -> LoadingContent(
            modifier = modifier
        )
        is Success -> CatalogScreenContent(
            pizzas = (state as Success).pizzas,
            listState = rememberLazyListState(),
            onPizzaClick = { id -> toPizzaScreen(id) },
            modifier = modifier
        )
    }
}
