package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.Typography
import com.example.ui.background
import com.example.ui.foreground

@Composable
fun LabelTab(
    selected: Boolean,
    onTabClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    selectedColor: Color = background,
    unselectedColor: Color = Transparent,
    titleStyle: TextStyle = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
    titleColor: Color = foreground
) {
    Box(
        modifier = modifier
            .then(
                if (selected) {
                    Modifier
                        .dropShadow(
                            shape = RoundedCornerShape(9999.dp)
                        ) {
                            radius = 3f
                            color = Black.copy(alpha = 0.1f)
                            offset = Offset(0f, 1f)
                            spread = 0f
                        }
                        .dropShadow(
                            shape = RoundedCornerShape(9999.dp)
                        ) {
                            radius = 2f
                            color = Black.copy(alpha = 0.1f)
                            offset = Offset(0f, 1f)
                            spread = -1f
                        }
                } else Modifier
            )
            .clip(shape = RoundedCornerShape(9999.dp))
            .background(
                color = if (selected) selectedColor else unselectedColor,
                shape = RoundedCornerShape(9999.dp)
            )
            .clickable {
                onTabClick()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp
            )
            .defaultMinSize(minHeight = 29.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = titleStyle,
            color = titleColor
        )
    }
}
