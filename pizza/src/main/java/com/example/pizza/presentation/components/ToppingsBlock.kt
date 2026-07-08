package com.example.pizza.presentation.components

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
import com.example.pizza.R.string.ingredient_bacon
import com.example.pizza.R.string.ingredient_basil
import com.example.pizza.R.string.ingredient_cheddar
import com.example.pizza.R.string.ingredient_chicken_fillet
import com.example.pizza.R.string.ingredient_chile
import com.example.pizza.R.string.ingredient_feta
import com.example.pizza.R.string.ingredient_green_pepper
import com.example.pizza.R.string.ingredient_ham
import com.example.pizza.R.string.ingredient_meatballs
import com.example.pizza.R.string.ingredient_mozzarella
import com.example.pizza.R.string.ingredient_mushrooms
import com.example.pizza.R.string.ingredient_onion
import com.example.pizza.R.string.ingredient_parmesan
import com.example.pizza.R.string.ingredient_pepperoni
import com.example.pizza.R.string.ingredient_pickle
import com.example.pizza.R.string.ingredient_pineapple
import com.example.pizza.R.string.ingredient_shrimp
import com.example.pizza.R.string.ingredient_tomato
import com.example.pizza.domain.Ingredient
import com.example.pizza.domain.Topping
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
        Ingredient.PINEAPPLE -> ingredient_pineapple
        Ingredient.MOZZARELLA -> ingredient_mozzarella
        Ingredient.PEPPERONI -> ingredient_pepperoni
        Ingredient.GREEN_PEPPER -> ingredient_green_pepper
        Ingredient.MUSHROOMS -> ingredient_mushrooms
        Ingredient.BASIL -> ingredient_basil
        Ingredient.CHEDDAR -> ingredient_cheddar
        Ingredient.PARMESAN -> ingredient_parmesan
        Ingredient.FETA -> ingredient_feta
        Ingredient.HAM -> ingredient_ham
        Ingredient.PICKLE -> ingredient_pickle
        Ingredient.TOMATO -> ingredient_tomato
        Ingredient.BACON -> ingredient_bacon
        Ingredient.ONION -> ingredient_onion
        Ingredient.CHILE -> ingredient_chile
        Ingredient.SHRIMP -> ingredient_shrimp
        Ingredient.CHICKEN_FILLET -> ingredient_chicken_fillet
        Ingredient.MEATBALLS -> ingredient_meatballs
    }
}
