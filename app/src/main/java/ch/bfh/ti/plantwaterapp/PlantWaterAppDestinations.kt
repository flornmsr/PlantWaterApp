package ch.bfh.ti.plantwaterapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 * Interface for each destination
 *
 * Each destination has a route and a bool value if its a detail page and an optional title
 */
interface PlantWaterAppDestination {
    val route: String
    val isDetailScreen: Boolean
    val title: Int?
}

/**
 * Interface for destinations with system icons in the Plant Watering App navigation.
 */
interface PlantWaterAppDestinationSystemIcon : PlantWaterAppDestination {
    val icon: ImageVector // The system icon associated with the destination.
}

/**
 * Interface for destinations with resource icons in the Plant Watering App navigation.
 */
interface PlantWaterAppDestinationResourceIcon : PlantWaterAppDestination {
    val icon: Int // The resource ID of the custom icon associated with the destination.
}

object PlantTodo: PlantWaterAppDestinationSystemIcon {
    override val route = "plant-todo"
    override val title = R.string.bottom_navigation_todo
    override val icon = Icons.Default.Notifications
    override val isDetailScreen = false
}

object PlantDetail: PlantWaterAppDestination{
    override val route = "plant-detail"
    override val title = null
    const val plantIdArg = "plant_id"
    val routeWithArgs = "${route}/{${plantIdArg}}"
    val arguments = listOf(
        navArgument(plantIdArg) { type = NavType.StringType }
    )
    override val isDetailScreen = true
}

object PlantOverview: PlantWaterAppDestinationResourceIcon{
    override val route = "plant-overview"
    override val title = R.string.bottom_navigation_all_plants
    override val icon = R.drawable.potted_plant
    override val isDetailScreen = false
}

// list with all app screens
val plantWaterAppScreens = listOf(PlantTodo, PlantOverview, PlantDetail)