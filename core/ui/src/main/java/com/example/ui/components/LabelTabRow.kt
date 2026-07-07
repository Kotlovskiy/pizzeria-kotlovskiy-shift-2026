package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.Typography
import com.example.ui.background
import com.example.ui.foreground
import com.example.ui.muted

@Composable
fun LabelTabRow(
    selectedTabIndex: Int,
    tabs: List<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = muted,
    selectedColor: Color = background,
    unselectedColor: Color = Transparent,
    titleStyle: TextStyle = Typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
    titleColor: Color = foreground
) {
    Row(
        modifier = modifier
            .background(color = containerColor, shape = RoundedCornerShape(9999.dp))
            .padding(4.dp),
    ) {
        tabs.forEachIndexed { index, title ->
            val selected = selectedTabIndex == index
            LabelTab(
                selected = selected,
                onTabClick = { onTabClick(index) },
                title = title,
                modifier = Modifier.weight(1f),
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                titleStyle = titleStyle,
                titleColor = titleColor
            )
        }
    }
}
