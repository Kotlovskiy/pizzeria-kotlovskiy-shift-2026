package com.example.pizza.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pizza.R.string.parameters
import com.example.ui.Typography
import com.example.ui.components.PizzaDescription
import com.example.ui.components.PizzaName
import com.example.ui.components.PizzaParameters
import com.example.ui.foreground

@Composable
fun PizzaInfoHeader(
    name: String,
    diameter: Int,
    dough: String,
    description: String,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp)
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        PizzaName(
            title = name,
            style = Typography.titleMedium
        )

        PizzaParameters(
            parameters = stringResource(parameters, diameter, dough)
        )

        PizzaDescription(
            description = description,
            style = Typography.bodySmall,
            color = foreground
        )
    }
}
