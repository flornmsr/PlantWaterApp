package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ch.bfh.ti.plantwaterapp.R
import ch.bfh.ti.plantwaterapp.ui.theme.PlantWaterAppTheme

/**
 * Composable that displays the top app bar for the Plant Watering application.
 *
 * Currently, all the Material 3 top app bar APIs are experimental,
 * so we have to add the @OptIn(ExperimentalMaterial3Api::class) annotation
 *
 * When you use @OptIn(ExperimentalMaterial3Api::class),
 * you are explicitly acknowledging that you are using an experimental feature from the Material3 library in your code
 *
 * @param modifier Modifier for custom styling and layout options.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantWaterAppTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(text = stringResource(id = R.string.app_name))
                Icon(
                    // use a custom icon from the drawable resources with the ID of the imported icon
                    painter = painterResource(id = R.drawable.potted_plant),
                    contentDescription = null
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun PlantWaterAppTopBarPreview() {
    PlantWaterAppTheme {
        PlantWaterAppTopBar()
    }
}