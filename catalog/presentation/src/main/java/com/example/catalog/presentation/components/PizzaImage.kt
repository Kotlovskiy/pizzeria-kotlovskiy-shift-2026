package com.example.catalog.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import com.example.catalog.presentation.R
import com.example.ui.neutral200

@Composable
fun PizzaImage(
    image: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier,
        model = image,
        contentDescription = stringResource(R.string.pizza_icon),
        fallback = ColorPainter(neutral200),
        error = ColorPainter(neutral200),
        placeholder = ColorPainter(neutral200),
        contentScale = ContentScale.Crop
    )
}