package ch.bfh.ti.plantwaterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ch.bfh.ti.plantwaterapp.model.Plant
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppNavHost
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppNavigation
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppTopBar
import ch.bfh.ti.plantwaterapp.ui.common.navigateSingleTopTo
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantWaterApp()
        }
    }
}

@Composable
fun PlantWaterApp() {
    PlantWaterAppTheme {
        // keeps track of back stack composable entries
        val navController = rememberNavController()
        // get real time updates on your current destination from the back stack
        val currentBackStack by navController.currentBackStackEntryAsState()
        // Fetch your currentDestination:
        val currentDestination = currentBackStack?.destination
        // Change the variable to this and use PlantTodo as a backup screen if this returns null
        val currentScreen =
            plantWaterAppScreens.find { screens ->
                currentDestination?.route?.contains(screens.route) == true
            } ?: PlantTodo


        Scaffold(
            bottomBar = {
                // don't show if its a detail-screen
                if(!currentScreen.isDetailScreen) {
                    PlantWaterAppNavigation(
                        allScreens = plantWaterAppScreens.filter { !it.isDetailScreen },
                        currentScreen = currentScreen,
                        onNavigationClick = { newScreen ->
                            navController.navigateSingleTopTo(
                                newScreen.route
                            )
                        })
                }
            },
            topBar = {
                // don't show if its a detail-screen
                if(!currentScreen.isDetailScreen) {
                    PlantWaterAppTopBar()
                }
            },
        ) { innerPadding ->
            PlantWaterAppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding) // this padding is important. vertical padding is added at the top (height of TopBar) and bottom (height of bottomBar)
            )
        }

    }
}


public val dummyPlants = listOf<Plant>(
    Plant("Aloe Vera", "Living Room", R.drawable.aloe_vera, false),
    Plant("Cactus", "Living Room", R.drawable.cactus, false),
    Plant("Pilea Cadierei", "Living Room", R.drawable.pilea_cadierei, false),
    Plant("Bonsai", "Living Room", R.drawable.bonsai, true),
    Plant("Parlor Palm", "Floor", R.drawable.parlor_palm, false),
    Plant("Basil", "Kitchen", R.drawable.basil, true),
    Plant("Parsley", "Kitchen", R.drawable.parsley, false),
    Plant("Cactus", "Living Room", R.drawable.cactus, false),
    Plant("Cactus", "Living Room", R.drawable.cactus, false),
    Plant("Bonsai", "Floor", R.drawable.bonsai, true),
    Plant("Cactus", "Living Room", R.drawable.cactus, false),
    Plant("Pilea Cadierei", "Bedroom", R.drawable.pilea_cadierei, false),
    Plant("Parlor Palm", "Bedroom", R.drawable.parlor_palm, false),
    Plant("Aloe Vera", "Bedroom", R.drawable.aloe_vera, false),
    Plant("Basil", "Kitchen", R.drawable.basil, true),
    Plant("Parsley", "Kitchen", R.drawable.parsley, false)
)