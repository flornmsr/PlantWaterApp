package ch.bfh.ti.plantwaterapp.ui.plantdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ch.bfh.ti.plantwaterapp.PlantDetail
import ch.bfh.ti.plantwaterapp.dummyPlants
import ch.bfh.ti.plantwaterapp.model.Plant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * The `PlantDetailViewModel` class is responsible for managing the UI-related data and logic for the Plant Detail screen.
 *
 * Here we use MutableStateFlow. Advantages if you use the State Flows:
 *  - your viewmodel is compose-free and they are more reusable
 *  - you can use flow operators (like map, filter combine)
 *  - better handling of process death is easy implementable
 *
 * This doesn't play such a big role for this demo app. The other ViewModels use the compose state and not the MutableStateFlow.
 *
 * @param savedStateHandle A `SavedStateHandle` to retrieve and store data associated with the ViewModel during configuration changes.
 *                         In this context it is used to retrieve the plant ID argument from the navigation.
 */
class PlantDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    // It uses the get of savedStateHandle function to retrieve the plant ID argument that was passed during navigation.
    // The SavedStateHandle associated with a NavBackStackEntry is automatically populated with the navigation arguments.
    val plantId: Int = savedStateHandle.get<Int>(PlantDetail.plantIdArg)!!


    /**
     * A `MutableStateFlow` representing the current plant being displayed on the Plant Detail screen.
     * The state flow is exposed as an immutable `asStateFlow` to observe changes from the UI.
     */
    private val _plant = MutableStateFlow(getPlantById(plantId))
    val plant = _plant.asStateFlow()


    /**
     * Retrieves a plant from the `dummyPlants` list based on the provided ID.
     *
     * @param id The ID of the plant to retrieve.
     * @return The retrieved plant.
     * @throws NoSuchElementException If no plant is found with the specified ID.
     */
    fun getPlantById(id: Int): Plant {
        return dummyPlants.find { it.id == id } ?: throw  NoSuchElementException("Plant not found with ID: $id")

    }

}

