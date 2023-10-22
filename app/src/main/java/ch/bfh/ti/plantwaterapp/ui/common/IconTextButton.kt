package ch.bfh.ti.plantwaterapp.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

/**
 * A Composable that represents a button with an icon and a text label.
 *
 * @param icon The vector icon to be displayed on the button.
 * @param text The resource ID for the text label of the button.
 *             @StringRes indicates that the param is expected to be a resource reference to a string in your Android application.
 * @param onClick The callback function to be invoked when the button is clicked.
 * @param modifier Modifier for custom styling and layout options.
 */
@Composable
fun IconTextButton(
    icon: ImageVector,
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(onClick = { onClick() }, modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null) // text below describes enough the meaning, usually you should add description for accessibility
            Text(stringResource(text))
        }
    }
}