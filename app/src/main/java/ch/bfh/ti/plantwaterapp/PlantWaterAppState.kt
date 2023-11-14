package ch.bfh.ti.plantwaterapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

/**
 * `PlantWaterAppState` is a state holder class designed to manage the UI state within a Jetpack Compose application
 * following the principles of the separation of concerns. It provides a clear and structured approach to handling
 * navigation and UI state, making the code easier to read and understand.
 *
 * @param navController The NavController responsible for managing the navigation within the application.
 */
class PlantWaterAppState(val navController: NavController){

    /**
     * Computed property using the @Composable annotation to represent the current screen in the application.
     * It dynamically finds the current screen by comparing the routes with the screens in `plantWaterAppScreens`.
     * If no match is found, it defaults to `PlantTodo`.
     *
     * @return The current screen destination.
     */
    val currentScreen: PlantWaterAppDestination
        @Composable get() = plantWaterAppScreens.find { screens ->
            // get real time updates on your current destination from the back stack
            val currentBackStack by navController.currentBackStackEntryAsState()
            // Fetch your currentDestination:
            val currentDestination = currentBackStack?.destination
            // compares the routes and returns true if destination route is contained
            currentDestination?.route?.contains(screens.route) == true
        } ?: PlantTodo

    /**
     * Computed property using the @Composable annotation to determine whether the navbar and header should be shown.
     * It checks if the current route is contained in the routes designated for the full screen in `navBarAndHeaderScreens`.
     *
     * @return `true` if the navbar and header should be shown, `false` otherwise.
     */
    val shouldShowNavBarAndHeader: Boolean
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in navBarAndHeaderScreens.map { it.route }

}