package ch.bfh.ti.plantwaterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import ch.bfh.ti.plantwaterapp.model.Plant
import ch.bfh.ti.plantwaterapp.ui.plantdetail.DetailScreen
import ch.bfh.ti.plantwaterapp.ui.plantoverview.PlantOverviewScreen
import ch.bfh.ti.plantwaterapp.ui.planttodo.TodoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by rememberSaveable { mutableStateOf(1) }
            var plant by rememberSaveable { mutableStateOf("") }
            when (screen) {
                1 -> TodoScreen(
                    onNavigationClick = { screen = 3 },
                    onNavigateToDetail = { plantName ->
                        plant = plantName
                        screen = 2
                    })

                2 -> DetailScreen(plantNane = plant, onNavigateBack = { screen = 1 })
                3 -> PlantOverviewScreen(onNavigationClick = { screen = 1 })
            }
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