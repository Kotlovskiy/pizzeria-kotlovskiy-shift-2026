package com.example.pizza.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizza.domain.Ingredient
import com.example.pizza.domain.Topping
import com.example.pizza.presentation.components.PizzaInfo
import com.example.ui.components.PizzaImage

@Composable
fun PizzaScreenContent(
    image: String,
    imageSize: Dp,
    name: String,
    diameter: Int,
    dough: String,
    description: String,
    selectedTabIndex: Int,
    tabs: List<String>,
    onTabClick: (Int) -> Unit,
    onToppingClick: (Ingredient) -> Unit,
    allToppings: List<Topping>,
    selectedToppings: List<Ingredient>,
    onButtonClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues =
        PaddingValues(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 24.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(32.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            PizzaImage(
                image = image,
                modifier = Modifier.size(imageSize)
            )
        }

        item {
            PizzaInfo(
                modifier = Modifier.fillMaxWidth(),
                name = name,
                diameter = diameter,
                dough = dough,
                description = description,
                selectedTabIndex = selectedTabIndex,
                tabs = tabs,
                onTabClick = onTabClick,
                onToppingClick = onToppingClick,
                allToppings = allToppings,
                selectedToppings = selectedToppings,
                onButtonClick = onButtonClick,
                buttonText = buttonText
            )
        }
    }
}
