package ch.bfh.ti.plantwaterapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.bfh.ti.plantwaterapp.R
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme


/**
 * Composable that represents a plant card.
 *
 * @param drawable The resource ID of the drawable to display. Should be annotated with @DrawableRes.
 *                 Indicates that the param is expected to be a resource reference to a drawable in your Android application.
 *                 It's commonly used to improve code clarity and provide static analysis tools with information about the expected type of resource.
 * @param title    Name of the plant
 * @param onNavigateToDetail onNavigateToDetail Callback function to handle navigation to the plant detail screen when clicking on the card.
 *                           It should take a single parameter, which is the title of the plant to navigate to.
 * @param modifier Modifier for customizing the appearance and behavior of the element, should be the first optional parameter!
 * @param subtitle Optional subtitle, e.g., the location of the plant.
 * @param cardEndContent An optional composable that allows adding custom content at the end of the card.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantCard(
    @DrawableRes drawable: Int,
    title: String,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    cardEndContent: @Composable () -> Unit = {}
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        onClick = { onNavigateToDetail(title) },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // everything is centered vertically
            modifier = Modifier.fillMaxWidth() // use as much width as possible
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = "image of the plant $title",
                contentScale = ContentScale.Crop, // Crops the image so that it fits into the intended area.
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp) // adds a space between image and text
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                )
                // because subtitle is optional, only show it, if there is a subtitle
                // This is using the Kotlin safe call operator (?.). It means that if subTitle is not null,
                // the code block inside the let function will be executed. If subTitle is null, nothing happens.
                subtitle?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f)) // Pushes the following element to the end
            cardEndContent()
        }
    }
}

@Preview
@Composable
fun PlantCardPreview() {
    PlantWaterAppTheme {
        PlantCard(
            drawable = R.drawable.bonsai,
            title = "My lovely Bonsai",
            subtitle = "kitchen",
            onNavigateToDetail = {})
            {
                WateringStateIconRow(
                    isWateredState = false,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
    }
}