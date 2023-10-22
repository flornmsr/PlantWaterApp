package ch.bfh.ti.plantwaterapp.ui.planttodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.bfh.ti.plantwaterapp.R
import ch.bfh.ti.plantwaterapp.dummyPlants
import ch.bfh.ti.plantwaterapp.ui.common.PlantCard
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppNavigation
import ch.bfh.ti.plantwaterapp.ui.common.PlantWaterAppTopBar
import ch.bfh.ti.plantwaterapp.ui.common.WateringStateIconRow
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme

/**
 * todo: will be finished when proper navigation is implemented
 */
@Composable
fun TodoScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigationClick: () -> Unit
) {
    PlantWaterAppTheme {
        Scaffold(
            bottomBar = { PlantWaterAppNavigation(onNavigationClick) },
            topBar = { PlantWaterAppTopBar() },
        ) { padding ->
            TodoScreenContent(
                onNavigateToDetail = onNavigateToDetail,
                Modifier.padding(padding) // this padding is important. vertical padding is added at the top (height of TopBar) and bottom (height of bottomBar)
            )
        }
    }
}

/**
 * Composable that represents the content of the to-do list screen, including the header and the list of to-do items.
 *
 * @param onNavigateToDetail Callback function to navigate to the detail view of a to-do item.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun TodoScreenContent(
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        // Calculate the percentage of watering tasks done
        val taskProgressPercentage = dummyPlants.count { it.isWatered } * 100 / dummyPlants.count()
        TodoHeader(
            taskProgressPercentage = taskProgressPercentage,
            Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        TodoList(onNavigateToDetail = onNavigateToDetail)
    }
}

/**
 * Composable that displays a list of to-do items using a LazyColumn.
 *
 * @param onNavigateToDetail Callback function to navigate to the detail view of a to-do item.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun TodoList(
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
            .fillMaxHeight()
    ) {
        items(dummyPlants) { item ->
            PlantCard(
                drawable = item.imageId,
                title = item.name,
                subtitle = item.location,
                onNavigateToDetail = onNavigateToDetail,
                modifier = Modifier.height(80.dp)
                // instead of the content in the brackets below:
                // cardEndContent = { WateringStateIconRow(...) }
            ) {
                WateringStateIconRow(
                    wateringState = item.isWatered,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}

/**
 * Composable that displays the header for a to-do list, including the title and task progress percentage.
 * The default color of the Card is overwritten, with the secondaryContainer color of the [MaterialTheme.colorScheme],
 * so that there is a visual difference between the TodoList-cards and this header card
 *
 * @param taskProgressPercentage The percentage of completed tasks to be displayed.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun TodoHeader(taskProgressPercentage: Int, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = modifier
    ) {
        Row(
            // there will be space between the child elements, and the space will be distributed evenly,
            // pushing the first element to the beginning of the row and the last element to the end.
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.todo_screen_title),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(text = "$taskProgressPercentage%")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoScreenPreview() {
    TodoScreen(onNavigationClick = {}, onNavigateToDetail = {})
}