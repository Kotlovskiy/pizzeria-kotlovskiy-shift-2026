package com.example.catalog.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cheonjaeung.compose.grid.SimpleGridCells
import com.cheonjaeung.compose.grid.VerticalGrid
import com.example.catalog.presentation.R
import com.example.domain.Ingredient
import com.example.domain.Topping
import com.example.ui.Typography
import com.example.ui.foreground

@Composable
fun ToppingsBlock(
    title: String,
    onToppingClick: (Ingredient) -> Unit,
    allToppings: List<Topping>,
    selectedToppings: List<Ingredient>,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(16.dp),
    titleStyle: TextStyle = Typography.bodySmall,
    titleColor: Color = foreground,
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        Text(
            text = title,
            style = titleStyle,
            color = titleColor
        )

        VerticalGrid(
            columns = SimpleGridCells.Adaptive(minSize = 104.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            allToppings.forEach { topping ->
                ToppingCard(
                    image = topping.image,
                    title = stringResource(getIngredientName(topping.type)),
                    price = topping.price,
                    onClick = { onToppingClick(topping.type) },
                    selected = topping.type in selectedToppings,
                    modifier = Modifier.widthIn(min = 104.dp, max = 104.dp)
                )
            }
        }
    }
}

fun getIngredientName(ingredient: Ingredient): Int {
    return when (ingredient) {
        Ingredient.PINEAPPLE -> R.string.ingredient_pineapple
        Ingredient.MOZZARELLA -> R.string.ingredient_mozzarella
        Ingredient.PEPPERONI -> R.string.ingredient_pepperoni
        Ingredient.GREEN_PEPPER -> R.string.ingredient_green_pepper
        Ingredient.MUSHROOMS -> R.string.ingredient_mushrooms
        Ingredient.BASIL -> R.string.ingredient_basil
        Ingredient.CHEDDAR -> R.string.ingredient_cheddar
        Ingredient.PARMESAN -> R.string.ingredient_parmesan
        Ingredient.FETA -> R.string.ingredient_feta
        Ingredient.HAM -> R.string.ingredient_ham
        Ingredient.PICKLE -> R.string.ingredient_pickle
        Ingredient.TOMATO -> R.string.ingredient_tomato
        Ingredient.BACON -> R.string.ingredient_bacon
        Ingredient.ONION -> R.string.ingredient_onion
        Ingredient.CHILE -> R.string.ingredient_chile
        Ingredient.SHRIMP -> R.string.ingredient_shrimp
        Ingredient.CHICKEN_FILLET -> R.string.ingredient_chicken_fillet
        Ingredient.MEATBALLS -> R.string.ingredient_meatballs
    }
}
