package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ch.bfh.ti.plantwaterapp.R

/**
 * Composable that displays a row of icons representing the watering state of a plant.
 *
 * @param isWateredState A boolean value indicating whether the plant needs watering (false) or not (true).
 * @param modifier Modifier for custom styling and layout options.
 * @param onClick callback which can be used for click events
 */
@Composable
fun WateringStateIconRow(
    isWateredState: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    // the color variable is animated. If the isWateredState changes, then the color changes also. Changing the colour is animated and therefore smoother
    val color by animateColorAsState(if(isWateredState) Color(0.31f, 0.765f, 0.969f, 1.0f) else MaterialTheme.colorScheme.error)

    // make the row clickable with this modifier
    Row(modifier.clickable {  onClick() }) {
        Icon(
            painterResource(id = R.drawable.potted_plant),
            contentDescription = stringResource(id = R.string.common_desc_state_icon_plant),
            tint = color
        )
        if (isWateredState) {
            Icon(
                Icons.Default.WaterDrop,
                contentDescription = stringResource(id = R.string.common_desc_state_icon_water_not_needed),
                tint = color
            )
        } else {
            Icon(
                painterResource(id = R.drawable.humidity_low),
                contentDescription = stringResource(id = R.string.common_desc_state_icon_water_needed),
                tint = color
            )
        }
    }
}