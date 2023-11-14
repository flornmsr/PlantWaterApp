package ch.bfh.ti.plantwaterapp.ui.plantdetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Undo
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.bfh.ti.plantwaterapp.R
import ch.bfh.ti.plantwaterapp.model.Plant
import ch.bfh.ti.plantwaterapp.ui.common.IconTextButton
import ch.bfh.ti.plantwaterapp.ui.common.WateringStateIconRow
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme

/**
 * Composable that displays details of a plant.
 *
 * @param plant The plant data to be displayed.
 * @param onNavigateBack Callback function to navigate back to the previous screen.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantDetailsScreen(
    plant: Plant,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Box-Layout is used: Icon is placed on the top of the detail content
    Box(modifier.fillMaxSize()) {
        PlantDetailsContent(plant)
        FilledIconButton(
            onClick = onNavigateBack,
            Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.common_desc_navigate_back),
            )
        }
    }
}

/**
 * Composable that displays the content of plant details, including an image and plant information.
 * The [PlantInfo] is as big as its content. The [Image] fills the rest of the free screen space
 * @param plant The plant data to be displayed.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantDetailsContent(plant: Plant, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = plant.imageId),
            contentDescription = "Image of a ${plant.name}",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f) // This item takes the remaining vertical space
        )
        PlantInfo(plant = plant)
    }
}

/**
 * Composable that displays information about a plant, including its name, location, and description.
 *
 * @param plant The plant data to be displayed.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantInfo(plant: Plant, modifier: Modifier = Modifier) {

    var isWateredState by rememberSaveable {
        mutableStateOf(plant.isWatered)
    }

    // saves the state, if the details of a plant is visible or not
    var showDetailsState by remember {
        mutableStateOf(true)
    }
    // By default, the tonal and shadow elevation for the surface is 0.dp.
    // Tonal elevation of 5.dp is provided to give the surface a tonal color of primary slot
    Surface(tonalElevation = 5.dp, modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            PlantInfoTitle(
                title = plant.name,
                subtitle = plant.location,
                isWateredState = isWateredState,
                // pass down state, it is used do display the correct icon
                showDetails = showDetailsState,
                // change the state in this composable via callback-function.
                onToggleDetails = { showDetailsState = !showDetailsState }
            )
            // With this Composable you can make the content visible or hide it
            // The Composable animates the state changes. If no custom config is added like here,
            // it takes the default config and animates the the appearance and disappearance of the content
            AnimatedVisibility(visible = showDetailsState) {
                PlantInfoDetails(
                    isWateredState = isWateredState,
                    onWateredClicked = { isWateredState = !isWateredState })
            }

        }
    }
}

/**
 * Shows the description of a Plant and has a bar with actions for the plant
 *
 *  @param isWateredState A boolean indicating whether the plant has been watered.
 *  @param onWateredClicked Callback function for the action when the user has watered the plant.
 *  @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantInfoDetails(
    isWateredState: Boolean,
    onWateredClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = "Description ", style = MaterialTheme.typography.titleMedium)
        Text(text = stringResource(id = R.string.detail_view_plant_description_placeholder))
        // Inserts a gap with the height of 32.dp to visually separate the components from each other.
        Spacer(modifier = Modifier.height(32.dp))
        PlantActionBar(
            isWateredState = isWateredState,
            onWateredClicked = onWateredClicked
        )
    }
}


/**
 * Composable that displays the title and subtitle of a plant
 * The text is centered horizontally
 *
 * @param title The name of the plant.
 * @param subtitle The location of the plant.
 * @param isWateredState A boolean indicating whether the plant has been watered.
 * @param showDetails A boolean indicating if the details are shown
 * @param onToggleDetails Callback function for the action when the user wants to toggle the details
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantInfoTitle(
    title: String,
    subtitle: String,
    isWateredState: Boolean,
    showDetails: Boolean,
    onToggleDetails: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        WateringStateIconRow(isWateredState = isWateredState)
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        Text(text = subtitle, style = MaterialTheme.typography.headlineSmall)
        IconButton(onClick = { onToggleDetails() }) {
            Icon(
                if (showDetails) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
                contentDescription = "${stringResource(R.string.common_desc_toggle_details)}: $showDetails"
            )
        }
    }
}

/**
 * Composable that represents an action bar for plant-related actions.
 * The Buttons inside the bar have no function at the moment
 *
 * @param isWateredState A boolean value indicating whether the plant needs watering (true) or not (false).
 * @param onWateredClicked Callback function for the action when the user has watered the plant.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantActionBar(
    isWateredState: Boolean,
    onWateredClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        /** weight is only available in this RowScope and not inside the child composable
         * All Buttons should have the same width, so pass the same modifier to each Button
         **/
        val buttonModifier = Modifier.weight(1f)
        IconTextButton(
            icon = if (isWateredState) Icons.Filled.Undo else Icons.Filled.Check,
            text = if (isWateredState) R.string.todo_undone else R.string.todo_done,
            onClick = onWateredClicked,
            modifier = buttonModifier
        )
        IconTextButton(
            icon = Icons.Filled.Edit,
            text = R.string.todo_edit,
            onClick = {/*TODO*/ },
            modifier = buttonModifier
        )
        IconTextButton(
            icon = Icons.Filled.Delete,
            text = R.string.todo_delete,
            onClick = {/*TODO*/ },
            modifier = buttonModifier
        )
    }
}


@Preview
@Composable
private fun PlantDetailContentPreview() {
    PlantWaterAppTheme {
        PlantDetailsScreen(
            plant = Plant(1,"Cactus", "Living Room", R.drawable.cactus, false),
            onNavigateBack = {}
        )
    }
}
