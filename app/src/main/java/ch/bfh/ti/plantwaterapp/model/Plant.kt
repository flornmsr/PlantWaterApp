package ch.bfh.ti.plantwaterapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Plant(val id: Int, val name: String, val location: String, val imageId: Int, isWateredInit: Boolean = false){

    /**
     * Compose is tracking for the MutableList (which we are using for displaying the plants) changes that are related to adding and removing elements.
     * It's unaware of changes in the row item values (isWatered in our case), so you have to track them too. (Delete and add wold work)
     * IsWatered becomes a MutableState<Boolean> which causes Compose to track an item change. Other option wold be to delete and re-add the item
     */

    var isWatered by mutableStateOf(isWateredInit)
}