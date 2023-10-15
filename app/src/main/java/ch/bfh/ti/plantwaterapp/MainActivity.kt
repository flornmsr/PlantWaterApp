package ch.bfh.ti.plantwaterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme

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
fun PlantOverview(modifier: Modifier = Modifier){
    Column {
        PlantOverviewItem(Plant("Cactus"))
        PlantOverviewItem(Plant("Bonsai"))
    }
}

@Composable
fun PlantOverviewItem(plant: Plant, modifier: Modifier = Modifier) {
    /** todo: add picture, name and location of a plant, use the Row-Composable. If the plant has not been watered, add a button to the row (Without functionality).
     *  It has 3 images in the resources. For example with the ID: R.drawable.cactus
     */
    Text(plant.name)
}

// Todo: extend the plant data class
data class Plant(val name: String)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlantWaterAppTheme {
        PlantOverview()
    }
}