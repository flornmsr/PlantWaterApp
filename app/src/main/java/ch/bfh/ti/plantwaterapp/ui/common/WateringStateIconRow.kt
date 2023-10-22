package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ch.bfh.ti.plantwaterapp.R

/**
 * Composable that displays a row of icons representing the watering state of a plant.
 *
 * @param wateringState A boolean value indicating whether the plant needs watering (true) or not (false).
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun WateringStateIconRow(
    wateringState: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        if (wateringState) {
            val tintColor = Color(0.31f, 0.765f, 0.969f, 1.0f)
            Icon(
                painterResource(id = R.drawable.potted_plant),
                contentDescription = null,
                tint = tintColor
            )
            Icon(
                Icons.Default.WaterDrop,
                contentDescription = null,
                tint = tintColor
            )
        } else {
            val tintColor = MaterialTheme.colorScheme.error
            Icon(
                painterResource(id = R.drawable.potted_plant),
                contentDescription = null,
                tint = tintColor
            )
            Icon(
                painterResource(id = R.drawable.humidity_low),
                contentDescription = null,
                tint = tintColor
            )
        }
    }
}