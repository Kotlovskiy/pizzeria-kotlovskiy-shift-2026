package com.example.catalog.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.ui.Typography
import com.example.ui.foreground

@Composable
fun PizzaName (
    title: String,
    style: TextStyle = Typography.bodyMedium,
    color: Color = foreground
) {
    Text(
        text = title,
        style = style,
        color = color
    )
}

@Composable
fun PizzaDescription (
    description: String,
    style: TextStyle = Typography.labelMedium,
    color: Color = foreground.copy(alpha = 0.5f)
) {
    Text(
        text = description,
        style = style,
        color = color
    )
}

@Composable
fun PizzaPrice (
    price: String,
    style: TextStyle = Typography.bodyMedium,
    color: Color = foreground
) {
    Text(
        text = price,
        style = style,
        color = color
    )
}

@Composable
fun PizzaParameters (
    parameters: String,
    style: TextStyle = Typography.labelMedium,
    color: Color = foreground.copy(alpha = 0.5f)
) {
    Text(
        text = parameters,
        style = style,
        color = color
    )
}

@Composable
fun ToppingName (
    title: String,
    style: TextStyle = Typography.labelMedium,
    color: Color = foreground
) {
    Text(
        text = title,
        style = style,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ToppingPrice (
    title: String,
    style: TextStyle = Typography.labelSmall,
    color: Color = foreground
) {
    Text(
        text = title,
        style = style,
        color = color
    )
}
