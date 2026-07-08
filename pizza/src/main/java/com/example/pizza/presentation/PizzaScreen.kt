package com.example.pizza.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.LoadingContent
import com.example.pizza.R.string.add_to_cart
import com.example.pizza.R.string.large_size
import com.example.pizza.R.string.medium_size
import com.example.pizza.R.string.save
import com.example.pizza.R.string.small_size
import com.example.pizza.R.string.thick_dough
import com.example.pizza.R.string.thin_dough
import com.example.pizza.domain.Dough
import com.example.pizza.domain.Size
import com.example.pizza.presentation.PizzaScreenError.NoInternet
import com.example.pizza.presentation.PizzaScreenError.ServerError
import com.example.pizza.presentation.PizzaScreenError.SmtWentWrong
import com.example.pizza.presentation.PizzaScreenState.Initial
import com.example.pizza.presentation.PizzaScreenState.LoadingContent
import com.example.pizza.presentation.PizzaScreenState.Content
import com.example.pizza.presentation.PizzaScreenState.Error
import com.example.ui.R.string.check_internet_and_retry
import com.example.ui.R.string.server_not_available
import com.example.ui.R.string.smt_went_wrong
import com.example.ui.R.string.try_again
import com.example.ui.errorContent.NoInternetContent
import com.example.ui.errorContent.ServerErrorContent
import com.example.ui.errorContent.SmtWentWrongContent

@Composable
fun PizzaScreen(
    modifier: Modifier = Modifier,
    viewModel: PizzaScreenViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state) {
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
        Initial -> {
            viewModel.loadData()
        }
        LoadingContent -> {
            LoadingContent(
                modifier = modifier
            )
        }
        is Content -> {
            PizzaScreenContent(
                image = (state as Content).pizzaInfo.image,
                imageSize = 220.dp,
                name = (state as Content).pizzaInfo.title,
                diameter = when ((state as Content).selectedSize.size) {
                    Size.SMALL -> 30
                    Size.MEDIUM -> 40
                    Size.LARGE -> 50
                },
                dough =
                    if ((state as Content).selectedDough.type == Dough.THIN)
                        stringResource(thin_dough)
                    else
                        stringResource(thick_dough),
                description = (state as Content).pizzaInfo.description,
                selectedTabIndex =
                    when ((state as Content).selectedSize.size) {
                        Size.SMALL -> 0
                        Size.MEDIUM -> 1
                        Size.LARGE -> 2
                    },
                tabs =
                    listOf(
                        stringResource(small_size),
                        stringResource(medium_size),
                        stringResource(large_size)
                    ),
                onTabClick =
                    { selected ->
                        when (selected) {
                            0 -> viewModel.updateSize(Size.SMALL)
                            1 -> viewModel.updateSize(Size.MEDIUM)
                            2 -> viewModel.updateSize(Size.LARGE)
                        }
                    },
                onToppingClick =
                    { ingredient ->
                        viewModel.updateToppings(ingredient)
                    },
                allToppings = (state as Content).allToppings,
                selectedToppings = (state as Content).selectedToppings,
                onButtonClick = { viewModel.clickButton() },
                buttonText =
                    if ((state as Content).isEditMode)
                        stringResource(save)
                    else
                        stringResource(add_to_cart),
                modifier = modifier
                    .then(
                        if ((state as Content).loading) {
                            Modifier
                                .drawWithContent {
                                    drawContent()
                                    drawRect(color = Black, alpha = 0.2f)
                                }
                        } else {
                            Modifier
                        }
                    )
            )
            if ((state as Content).loading) {
                LoadingContent(
                    modifier = modifier
                )
            }
        }

        PizzaScreenState.Navigation -> {}
    }
}
