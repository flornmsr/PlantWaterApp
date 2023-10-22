package ch.bfh.ti.plantwaterapp.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ch.bfh.ti.plantwaterapp.R

@Composable
fun PlantWaterAppNavigation(
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        // add one or more NavigationBarItem
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_todo)
                )
            },
            selected = true,
            onClick = { onNavigationClick() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    // use a custom icon from the drawable resources with the ID of the imported icon
                    painter = painterResource(id = R.drawable.potted_plant),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_all_plants)
                )
            },
            selected = false,
            onClick = { onNavigationClick() }
        )
    }
}