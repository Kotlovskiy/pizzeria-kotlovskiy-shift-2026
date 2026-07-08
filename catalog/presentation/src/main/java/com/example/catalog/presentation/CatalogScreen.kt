package com.example.catalog.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.LoadingContent
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
        CatalogScreenState.Initial -> viewModel.loadData()
        CatalogScreenState.Empty -> {}
        is CatalogScreenState.Error -> {
            when ((state as CatalogScreenState.Error).error) {
                CatalogScreenError.NoInternet -> NoInternetContent(
                    text = stringResource(check_internet_and_retry),
                    buttonText = stringResource(try_again),
                    onClickRetry = { viewModel.loadData() },
                    modifier = modifier.padding(horizontal = 32.dp)
                )
                CatalogScreenError.ServerError -> ServerErrorContent(
                    stringResource(server_not_available),
                    modifier = modifier.padding(horizontal = 32.dp)
                )
                CatalogScreenError.SmtWentWrong -> SmtWentWrongContent(
                    text = stringResource(smt_went_wrong),
                    buttonText = stringResource(try_again),
                    onClickRetry = { viewModel.loadData() },
                    modifier = modifier.padding(horizontal = 32.dp)
                )
            }
        }
        CatalogScreenState.Loading -> LoadingContent(
            modifier = modifier
        )
        is CatalogScreenState.Success -> CatalogScreenContent(
            pizzas = (state as CatalogScreenState.Success).pizzas,
            listState = rememberLazyListState(),
            onPizzaClick = { id -> toPizzaScreen(id) },
            modifier = modifier
        )
    }
}
