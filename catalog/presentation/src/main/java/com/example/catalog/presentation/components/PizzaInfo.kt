package com.example.catalog.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catalog.presentation.R.string.add_toppings
import com.example.domain.Ingredient
import com.example.domain.Topping
import com.example.ui.components.LabelTabRow
import com.example.ui.components.PrimaryButton

@Composable
fun PizzaInfo(
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
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(24.dp)
) {
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        PizzaInfoHeader(
            modifier = Modifier.fillMaxWidth(),
            name = name,
            diameter = diameter,
            dough = dough,
            description = description,
        )

        LabelTabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex,
            tabs = tabs,
            onTabClick = onTabClick
        )

        ToppingsBlock(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(add_toppings),
            onToppingClick = onToppingClick,
            allToppings = allToppings,
            selectedToppings = selectedToppings
        )

        PrimaryButton(
            modifier = Modifier.fillMaxWidth().height(52.dp),
            onClick = onButtonClick,
            text = buttonText
        )
    }
}
