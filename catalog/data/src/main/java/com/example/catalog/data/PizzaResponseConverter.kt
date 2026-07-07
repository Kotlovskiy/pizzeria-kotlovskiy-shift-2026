package com.example.catalog.data

import com.example.domain.Dough
import com.example.domain.DoughInfo
import com.example.domain.Ingredient
import com.example.domain.PizzaInfo
import com.example.domain.Size
import com.example.domain.SizeInfo
import com.example.domain.Topping
import com.example.network.NetworkConstants
import com.example.network.model.Dough.THICK
import com.example.network.model.Dough.THIN
import com.example.network.model.Ingredient.BACON
import com.example.network.model.Ingredient.BASIL
import com.example.network.model.Ingredient.CHEDDAR
import com.example.network.model.Ingredient.CHICKEN_FILLET
import com.example.network.model.Ingredient.CHILE
import com.example.network.model.Ingredient.FETA
import com.example.network.model.Ingredient.GREEN_PEPPER
import com.example.network.model.Ingredient.HAM
import com.example.network.model.Ingredient.MEATBALLS
import com.example.network.model.Ingredient.MOZZARELLA
import com.example.network.model.Ingredient.MUSHROOMS
import com.example.network.model.Ingredient.ONION
import com.example.network.model.Ingredient.PARMESAN
import com.example.network.model.Ingredient.PEPPERONI
import com.example.network.model.Ingredient.PICKLE
import com.example.network.model.Ingredient.PINEAPPLE
import com.example.network.model.Ingredient.SHRIMP
import com.example.network.model.Ingredient.TOMATO
import com.example.network.model.PizzaDough
import com.example.network.model.PizzaIngredient
import com.example.network.model.PizzaSize
import com.example.network.model.Size.LARGE
import com.example.network.model.Size.MEDIUM
import com.example.network.model.Size.SMALL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.network.model.Pizza as PizzaModel
import javax.inject.Inject
import kotlin.math.ceil

class PizzaResponseConverter @Inject constructor() {
    suspend fun convertPizzaResponse(pizzaResponse: List<PizzaModel>): List<PizzaInfo> {
        return withContext(Dispatchers.Default) {
            pizzaResponse.map { pizzaModel ->
                PizzaInfo(
                    id = pizzaModel.id,
                    image = getImageUrl(pizzaModel.img),
                    title = pizzaModel.name,
                    description = pizzaModel.description,
                    minPrice = getMinPrice(pizzaModel.sizes),
                    sizes = pizzaModel.sizes.map { convertPizzaSizeToSizeInfo(it) },
                    toppings = pizzaModel.toppings.map {
                        convertPizzaIngredientToTopping(it)
                    },
                    doughs = pizzaModel.doughs.map { convertPizzaDoughToDoughInfo(it) }
                )
            }
        }
    }

    private fun getMinPrice(sizes: List<PizzaSize>): Int {
        return ceil(sizes.last { it.type == SMALL }.price).toInt()
    }

    private fun getImageUrl(imagePath: String) =
        NetworkConstants.BASE_URL + "api" + imagePath
    
    private fun convertPizzaSizeToSizeInfo(size: PizzaSize): SizeInfo {
        return SizeInfo(
            size = when (size.type) {
                SMALL -> Size.SMALL
                MEDIUM -> Size.MEDIUM
                LARGE -> Size.LARGE
            },
            price = ceil(size.price).toInt()
        )
    }

    private fun convertPizzaIngredientToTopping(ingredient: PizzaIngredient): Topping {
        return Topping(
            image = getImageUrl(ingredient.img),
            price = ceil(ingredient.price).toInt(),
            type = when (ingredient.type) {
                PINEAPPLE -> Ingredient.PINEAPPLE
                MOZZARELLA -> Ingredient.MOZZARELLA
                PEPPERONI -> Ingredient.PEPPERONI
                GREEN_PEPPER -> Ingredient.GREEN_PEPPER
                MUSHROOMS -> Ingredient.MUSHROOMS
                BASIL -> Ingredient.BASIL
                CHEDDAR -> Ingredient.CHEDDAR
                PARMESAN -> Ingredient.PARMESAN
                FETA -> Ingredient.FETA
                HAM -> Ingredient.HAM
                PICKLE -> Ingredient.PICKLE
                TOMATO -> Ingredient.TOMATO
                BACON -> Ingredient.BACON
                ONION -> Ingredient.ONION
                CHILE -> Ingredient.CHILE
                SHRIMP -> Ingredient.SHRIMP
                CHICKEN_FILLET -> Ingredient.CHICKEN_FILLET
                MEATBALLS -> Ingredient.MEATBALLS
            }
        )
    }

    private fun convertPizzaDoughToDoughInfo(dough: PizzaDough): DoughInfo {
        return DoughInfo(
            type = when (dough.type) {
                THIN -> Dough.THIN
                THICK -> Dough.THICK
            },
            price = ceil(dough.price).toInt()
        )
    }
}
