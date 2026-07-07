package com.example.catalog.presentation.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.catalog.presentation.components.PizzaListItem
import com.example.domain.PizzaInfo

@Composable
fun CatalogScreenContent(
    pizzas: List<PizzaInfo>,
    listState: LazyListState,
    onPizzaClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(start = 16.dp, top = 32.dp, end = 16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp)
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        items(
            items = pizzas,
            key = { pizza -> pizza.id }
        ) { pizza ->
            PizzaListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onPizzaClick(pizza.id) }
                ,
                imageUrl = pizza.image,
                imageSize = 116.dp,
                title = pizza.title,
                description = pizza.description,
                price = pizza.minPrice
            )
        }
    }
}
