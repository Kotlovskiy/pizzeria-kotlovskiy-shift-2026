package com.example.ui.errorContent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ui.Typography
import com.example.ui.components.PrimaryButton
import com.example.ui.foreground

@Composable
fun NoInternetContent (
    text: String,
    buttonText:  String,
    onClickRetry: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = Typography.bodyMedium,
    textColor: Color = foreground,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            textAlign = TextAlign.Center
        )

        PrimaryButton(
            onClick = onClickRetry,
            text = buttonText
        )
    }
}
