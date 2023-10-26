package ch.bfh.ti.plantwaterapp.ui.plantoverview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.bfh.ti.plantwaterapp.R
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

    val allLocations = dummyPlants.map { it.location }.distinct()

    var activeLocations by rememberSaveable {
        mutableStateOf(allLocations)
    }

    // Lazy Column is used, so only Elements which are visible are rendered
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        // add the modifier here
        modifier = modifier
    ) {
        item {
            PlantOverviewSection(title = "Filter") {
                PlantFilter(
                    categories = allLocations,
                    activeCategories = activeLocations,
                    onFilterChanged = { clickedFilter ->
                        activeLocations = if (activeLocations.contains(clickedFilter)) {
                            activeLocations.filter { name -> name != clickedFilter }
                        } else {
                            activeLocations + clickedFilter
                        }
                    }
                )
            }
        }

        dummyPlants
            .groupBy { it.location }
            .forEach { (key, value) ->
                item {
                    // AnimatedVisibility with custom animations for entering and exiting
                    AnimatedVisibility(
                        // the item is visible if its key (location) is a memeber of the activeLocations-List
                        visible = activeLocations.contains(key),
                        // Duration of the vertical slide in is 1s and it starts with an offset of 600px.
                        // So it moves from 600px below up to the final position in 1s.
                        // Fade in with the initial alpha of 0.3f
                        enter = slideInVertically(animationSpec = tween(durationMillis = 1000), initialOffsetY = { +600 }) +
                                fadeIn( initialAlpha = 0.3f),
                        // Chain animations with "+"
                        exit = slideOutVertically() + shrinkHorizontally() + fadeOut()
                    ) {
                        PlantOverviewSection(title = key) {
                            PlantOverviewCardGrid(plants = value)
                        }
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

/**
 * A composable function for creating a filter-bar usinf FlowRow with [PlantFilterChip]-Items
 *
 * @param categories A list of filter categories to display.
 * @param activeCategories A list of currently active filter categories.
 * @param onFilterChanged A lambda function that will be called when a filter category is changed (selected/unselected).
 * @param modifier A modifier to customize the appearance and layout.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlantFilter(
    categories: List<String>,
    activeCategories: List<String>,
    onFilterChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .fillMaxWidth()
    ) {
        // If there are too many items for one row, additional rows are automatically created and the items added there
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
        ) {
            categories.forEach {
                PlantFilterChip(
                    filterName = it,
                    selected = activeCategories.contains(it),
                    onClick = onFilterChanged
                )
            }
        }
        if (activeCategories.isEmpty()) {
            Text(text = stringResource(R.string.overview_noting_selected), color = MaterialTheme.colorScheme.error)
        }
    }
}


/**
 * A composable function for displaying a filter chip representing a filter category for plant filtering.
 *
 * @param filterName The name of the filter category to display in the chip.
 * @param selected Indicates whether the filter chip is selected or not.
 * @param onClick A lambda function to be called when the filter chip is clicked.
 * @param modifier A modifier to customize the appearance and layout of the filter chip.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantFilterChip(
    filterName: String,
    selected: Boolean,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = { onClick(filterName) },
        label = { Text(text = filterName) },
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
        modifier = modifier
    )
}


@Preview
@Composable
fun PlantOverviewScreenPreview() {
    PlantOverviewScreen(onNavigationClick = {})
}