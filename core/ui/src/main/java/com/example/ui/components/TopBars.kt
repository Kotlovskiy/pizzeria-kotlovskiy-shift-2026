package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ui.R.drawable.chevron_left
import com.example.ui.R.string.back_button
import com.example.ui.Typography
import com.example.ui.foreground

@Composable
fun TitleTopBar(
    title: String,
    modifier: Modifier = Modifier,
    style: TextStyle = Typography.titleMedium,
    color: Color = foreground,
    padding: PaddingValues = PaddingValues(16.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp),
    onBackButtonClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier.padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        if (onBackButtonClick != null) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = onBackButtonClick
            ) {
                Icon(
                    painter = painterResource(chevron_left),
                    contentDescription = stringResource(back_button)
                )
            }
        }

        Text(
            text = title,
            style = style,
            color = color
        )
    }
}