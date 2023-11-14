package ch.bfh.ti.plantwaterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

        // the app stated is scoped and remembered in this composable because it was created here
        val appState = remember(navController){
            PlantWaterAppState(navController)
        }

        Scaffold(
            bottomBar = {
                // don't show if its a detail-screen
                if(appState.shouldShowNavBarAndHeader) {
                    PlantWaterAppNavigation(
                        allScreens = navBarAndHeaderScreens,
                        currentScreen = appState.currentScreen,
                        onNavigationClick = { newScreen ->
                            navController.navigateSingleTopTo(
                                newScreen.route
                            )
                        })
                }
            },
            topBar = {
                // don't show if its a detail-screen
                if(appState.shouldShowNavBarAndHeader) {
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


val dummyPlants = listOf(
    Plant(1,"Aloe Vera", "Living Room", R.drawable.aloe_vera),
    Plant(2,"Cactus", "Living Room", R.drawable.cactus, true),
    Plant(3,"Pilea Cadierei", "Living Room", R.drawable.pilea_cadierei),
    Plant(4,"Bonsai", "Living Room", R.drawable.bonsai),
    Plant(5,"Parlor Palm", "Floor", R.drawable.parlor_palm),
    Plant(6,"Basil", "Kitchen", R.drawable.basil),
    Plant(7,"Parsley", "Kitchen", R.drawable.parsley),
    Plant(8,"Cactus", "Living Room", R.drawable.cactus),
    Plant(9,"Cactus", "Living Room", R.drawable.cactus),
    Plant(10,"Bonsai", "Floor", R.drawable.bonsai),
    Plant(11,"Cactus", "Living Room", R.drawable.cactus),
    Plant(12,"Pilea Cadierei", "Bedroom", R.drawable.pilea_cadierei),
    Plant(13,"Parlor Palm", "Bedroom", R.drawable.parlor_palm),
    Plant(14,"Aloe Vera", "Bedroom", R.drawable.aloe_vera),
    Plant(15,"Basil", "Kitchen", R.drawable.basil),
    Plant(16,"Parsley", "Kitchen", R.drawable.parsley)
)