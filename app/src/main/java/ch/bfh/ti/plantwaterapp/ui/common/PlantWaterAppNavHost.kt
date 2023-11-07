package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ch.bfh.ti.plantwaterapp.PlantDetail
import ch.bfh.ti.plantwaterapp.PlantOverview
import ch.bfh.ti.plantwaterapp.PlantTodo
import ch.bfh.ti.plantwaterapp.ui.plantdetail.DetailScreen
import ch.bfh.ti.plantwaterapp.ui.plantoverview.PlantOverviewScreen
import ch.bfh.ti.plantwaterapp.ui.planttodo.TodoScreen

/**
 * Composable function that sets up the navigation host for the plant watering app.
 *
 * @param navController The NavHostController responsible for managing navigation within the app.
 * @param modifier Modifier to apply to the NavHost, including padding for top and bottom bars.
 */
@Composable
fun PlantWaterAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, // each NavController must be associated with a single NavHost
        startDestination = PlantTodo.route, // which destination to show when the app is launched: TodoScreen
        modifier = modifier // // this padding is important. vertical padding is added at the top (height of TopBar) and bottom (height of bottomBar): see scaffold in MainActivity
    ) {
        // defining and building the navigation graph

        // extension function to easily add individual composable destinations
        composable(route = PlantTodo.route) {
            //  actual UI to be displayed when you navigate to this destination
            TodoScreen(
                onNavigateToDetail = { navController.navigateSingleTopTo("${PlantDetail.route}/$it") }
            )
        }

        composable(
            route = PlantDetail.routeWithArgs, // route with arguments
            arguments = PlantDetail.arguments // provide arguments. In this case only the plantId
        ) { navBackStackEntry ->
            // Retrieve the passed argument
            val plantId = navBackStackEntry.arguments?.getString(PlantDetail.plantIdArg)

            // only when there is a plantId
            plantId?.let {
                DetailScreen(
                    onNavigateBack = { navController.navigateSingleTopTo(PlantTodo.route) },
                    plantNane = it
                )
            }
        }

        composable(route = PlantOverview.route) {
            PlantOverviewScreen(
                onNavigateToDetail = { navController.navigateSingleTopTo("${PlantDetail.route}/$it") }
            )
        }
    }
}

/**
 * Retapping the same tab in bottomBar launches the multiple copies of the same destination
 *
 * the launchSingleTop option is used to specify the behavior of navigating to a destination.
 * When launchSingleTop is set to true, it indicates that the destination should be launched as a single top instance.
 * This means that if the destination is already at the top of the back stack (i.e., it's the current destination),
 * it won't create a new instance of the destination but will instead just update its content
 */
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }
