package ch.bfh.ti.plantwaterapp.ui.planttodo

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import ch.bfh.ti.plantwaterapp.dummyPlants
import ch.bfh.ti.plantwaterapp.model.Plant

/**
 * The `PlantTodoViewModel` class is responsible for managing the UI-related data and logic for the Plant To-Do screen.
 * It provides access to the list of plants and a method to change the watering state of a specific plant.
 */
class PlantTodoViewModel : ViewModel() {

    /**
     * A mutable state list containing the plants displayed on the Plant To-Do screen.
     * This list can be modified to reflect changes in the UI. It can not be changed from outside.
     */
    var plants = dummyPlants.toMutableStateList()
        private set

    /**
     * Changes the watering state of a specific plant based on its ID.
     *
     * @param plantId The ID of the plant whose watering state needs to be changed.
     */
    fun changePlantWateringState(plantId: Int) =
        plants.find { it.id == plantId }?.let { plant: Plant ->
            plant.isWatered = !plant.isWatered
        }
}