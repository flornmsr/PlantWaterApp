package ch.bfh.ti.plantwaterapp.ui.plantoverview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.bfh.ti.plantwaterapp.dummyPlants
import ch.bfh.ti.plantwaterapp.model.Plant
import ch.bfh.ti.plantwaterapp.ui.common.PlantCard
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppNavigation
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppTopBar
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme


/**
 * todo: will be finished when proper navigation is implemented
 */
@Composable
fun PlantOverviewScreen(onNavigationClick: () -> Unit) {
    PlantWaterAppTheme {
        Scaffold(
            bottomBar = { PlantWaterAppNavigation(onNavigationClick) },
            topBar = { PlantWaterAppTopBar() },
        ) { padding ->
            // this padding is important. vertical padding is added at the top (height of TopBar) and bottom (height of bottomBar)
            PlantOverviewPerLocation(Modifier.padding(padding))
        }
    }
}

/**
 * Composable that displays plant overview sections grouped by location
 *
 * @param modifier Modifier for custom styling and layout options.
 *                 It's best practice to use this parameter to allow callers to modify the composable's appearance
 *                 So the composable is more flexible and reusable
 */
@Composable
fun PlantOverviewPerLocation(modifier: Modifier = Modifier) {
    // Lazy Column is used, so only Elements which are visible are rendered
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        // add the modifier here
        modifier = modifier
    ) {
        dummyPlants
            .groupBy { it.location }
            .forEach { (key, value) ->
                item {
                    PlantOverviewSection(title = key) {
                        PlantOverviewCardGrid(plants = value)
                    }
                }
            }
    }
}

/**
 * Composable that displays a grid of plants. Gird has always 2 rows, and n columns: GridCells.Fixed(2)
 *
 * @param plants A list of plant data to be displayed in the grid.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun PlantOverviewCardGrid(
    plants: List<Plant>,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        // adds padding to the rightmost and leftmost elements of the grid
        // Is done here on the content, if it were on the grid,
        // then when scrolling, the elements would not disappear at the edge of the screen but already at the edge of the padding.
        contentPadding = PaddingValues(horizontal = 16.dp),
        // Between the elements in the grid there is horizontally and vertically 16.dp space
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(plants) { plant ->
            PlantCard(
                drawable = plant.imageId,
                title = plant.name,
                onNavigateToDetail = {},
                modifier = Modifier.width(200.dp)
            )
        }
    }
}

/**
 * There are multiple sections that follow the same pattern
 * They each have a title, with some content varying depending on the section and some padding
 * This is called Slot APIs or slot-based layouts
 *
 * @param title The title of the section.
 * @param modifier Modifier for custom styling and layout options.
 * @param content A lambda function that defines the content to be displayed within the section. (Must be a composable)
 */
@Composable
fun PlantOverviewSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        // The title of a section always looks the same
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
        )
        // content is added here
        content()
    }
}

@Preview
@Composable
fun PlantOverviewScreenPreview() {
    PlantOverviewScreen(onNavigationClick = {})
}