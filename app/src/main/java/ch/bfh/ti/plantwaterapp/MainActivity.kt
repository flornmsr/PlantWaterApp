package ch.bfh.ti.plantwaterapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlantWaterAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlantOverview()
                }
            }
        }
    }
}

@Composable
fun PlantOverview(modifier: Modifier = Modifier) {
    Column {
        PlantOverviewItem(Plant("Cactus", "Living Room", R.drawable.cactus, false))
        PlantOverviewItem(Plant("Bonsai", "Floor", R.drawable.bonsai, true))
    }
}

@Composable
fun PlantOverviewItem(plant: Plant, modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = plant.imageId),
            contentDescription = "Plant: ${plant.name}"
        )
        Column {
            Text(plant.name)
            Text(plant.location)
        }
        if (!plant.isWatered) {
            Button(onClick = { Log.i("MainActivity", "Plant is watered") }) {
                Text("water")
            }
        }
    }
}

data class Plant(val name: String, val location: String, val imageId: Int, val isWatered: Boolean)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlantWaterAppTheme {
        PlantOverview()
    }
}