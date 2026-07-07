package com.example.catalog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catalog.presentation.R.string.topping_price
import com.example.ui.neutral50
import com.example.ui.selectedShadow
import com.example.ui.shadow

@Composable
fun ToppingCard(
    image: String,
    title: String,
    price: Int,
    onClick: () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(24.dp)
    Column(
        modifier = modifier
            .dropShadow(
                shape = shape
            ) {
                radius = 12f
                color = if (!selected) shadow.copy(alpha = 0.15f) else selectedShadow
                offset = Offset(0f, 4f)
                spread = 0f
            }
            .clip(shape = shape)
            .background(
                color = neutral50,
                shape = shape
            )
            .clickable { onClick() }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToppingImage(
            modifier = Modifier
                .size(88.dp),
            image = image
        )

        Spacer(Modifier.height(12.dp))

        ToppingName(title = title)

        Spacer(Modifier.height(4.dp))

        ToppingPrice(title = stringResource(topping_price, price))
    }
}
