package com.example.catalog.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.catalog.presentation.R
import com.example.ui.components.PizzaDescription
import com.example.ui.components.PizzaImage
import com.example.ui.components.PizzaName
import com.example.ui.components.PizzaPrice

@Composable
fun PizzaListItem(
    title: String,
    description: String,
    price: Int,
    imageUrl: String,
    imageSize: Dp,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(24.dp)
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement
    ) {
        PizzaImage(
            image = imageUrl,
            modifier = Modifier.size(imageSize)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            PizzaName(title)

            PizzaDescription(description)

            PizzaPrice(stringResource(R.string.price, price))
        }
    }
}
