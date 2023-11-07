package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ch.bfh.ti.plantwaterapp.PlantWaterAppDestination
import ch.bfh.ti.plantwaterapp.PlantWaterAppDestinationResourceIcon
import ch.bfh.ti.plantwaterapp.PlantWaterAppDestinationSystemIcon


/**
 * Composable function that displays a navigation bar for a plant watering app.
 *
 * @param allScreens List of all available navigation destinations/screens.
 * @param currentScreen The currently selected navigation destination/screen.
 * @param onNavigationClick Lambda function to handle navigation item clicks.
 * @param modifier Modifier to apply to the navigation bar.
 */
@Composable
fun PlantWaterAppNavigation(
    allScreens: List<PlantWaterAppDestination>,
    currentScreen: PlantWaterAppDestination,
    onNavigationClick: (PlantWaterAppDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {

        for (screen in allScreens) {
            NavigationBarItem(
                icon = {
                    // two different ways to show the icon: The first for System Icons, the second for icons from the resources
                    if (screen is PlantWaterAppDestinationSystemIcon) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null
                        )
                    } else if (screen is PlantWaterAppDestinationResourceIcon) {
                        Icon(
                            // use a custom icon from the drawable resources with the ID of the imported icon
                            painter = painterResource(id = screen.icon),
                            contentDescription = null
                        )
                    }

                },
                label = {
                    Text(
                        text = screen.title?.let { stringResource(it) } ?: ""
                    )
                },
                onClick = { onNavigationClick(screen) },
                selected = screen == currentScreen // the selected item is highlighted
            )
        }

    }
}