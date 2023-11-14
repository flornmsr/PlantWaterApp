package ch.bfh.ti.plantwaterapp.ui.plantoverview

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import ch.bfh.ti.plantwaterapp.dummyPlants


/**
 * The `PlantOverviewViewModel` class is responsible for managing the UI-related data and logic for the Plant Overview screen.
 * It provides access to the list of plants and methods to retrieve unique plant locations for filtering.
 *
 * This ViewModel uses the compose states directly. It's practically because its less code than with MutableStateFlow, we only have to declare one property.
 * In [ch.bfh.ti.plantwaterapp.ui.plantdetail.PlantDetailViewModel] there is an implementation with the MutableStateFlow
 */
class PlantOverviewViewModel : ViewModel() {


    /**
     * A mutable state list containing the plants displayed on the Plant Overview screen.
     * This list can be modified to reflect changes in the UI. It can not be changed from outside.
     */
    var plants = dummyPlants.toMutableStateList()
        private set

    /**
     * Retrieves all unique plant locations from the list of plants for filtering purposes.
     *
     * @return A list of distinct plant locations.
     */
    fun getAllLocations() = dummyPlants.map { it.location }.distinct()
}