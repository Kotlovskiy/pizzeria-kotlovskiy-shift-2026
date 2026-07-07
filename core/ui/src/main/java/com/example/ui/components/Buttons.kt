package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.Typography
import com.example.ui.primary
import com.example.ui.primaryForeground
import com.example.ui.primaryHover

data class AppButtonColors(
    val containerDefaultColor: Color,
    val containerActiveColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
)

fun defaultPrimaryButtonColors(): AppButtonColors {
    return AppButtonColors(
        containerDefaultColor = primary,
        containerActiveColor = primaryHover,
        contentColor = primaryForeground,
        disabledContainerColor = primary.copy(alpha = 0.2f),
        disabledContentColor = primaryForeground
    )
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(9999.dp),
    colors: AppButtonColors = defaultPrimaryButtonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    textStyle: TextStyle = Typography.labelMedium.copy(lineHeight = 21.sp),
    icon: ImageVector? = null,
    iconSize: Dp = 12.dp,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val animatedContainerColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledContainerColor
            isPressed || isHovered -> colors.containerActiveColor
            else -> colors.containerDefaultColor
        },
        animationSpec = tween(durationMillis = 200)
    )

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = ButtonColors(
            containerColor = animatedContainerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = animatedContainerColor,
            disabledContentColor = colors.contentColor
        ),
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = textStyle
        )

        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
