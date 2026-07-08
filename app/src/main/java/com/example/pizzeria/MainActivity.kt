package com.example.pizzeria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.catalog.presentation.CatalogDestination
import com.example.catalog.presentation.CatalogScreen
import com.example.pizza.presentation.PizzaDestination
import com.example.pizza.presentation.PizzaScreen
import com.example.pizza.presentation.PizzaScreenViewModel
import com.example.pizzeria.ui.theme.PizzeriaTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzeriaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(Modifier.fillMaxSize().padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(CatalogDestination)
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<CatalogDestination> {
                CatalogScreen(
                    modifier = Modifier.fillMaxSize(),
                    toPizzaScreen = { id ->
                        backStack.add(PizzaDestination(pizzaId = id))
                    }
                )
            }
            entry<PizzaDestination> { key ->
                val viewModel = hiltViewModel<PizzaScreenViewModel, PizzaScreenViewModel.Factory>(
                    key = key.hashCode().toString() + UUID.randomUUID().toString(),
                    creationCallback = { factory ->
                        factory.create(key)
                    }
                )

                PizzaScreen(
                    modifier = Modifier.fillMaxSize(),
                    viewModel = viewModel
                )
            }
        }
    )
}
