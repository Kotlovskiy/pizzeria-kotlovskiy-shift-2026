package com.example.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color.Companion.Black

fun Modifier.loadingOverlay(loading: Boolean): Modifier = this.then(
    if (loading) {
        Modifier.drawWithContent {
            drawContent()
            drawRect(color = Black, alpha = 0.2f)
        }
    } else {
        Modifier
    }
)
