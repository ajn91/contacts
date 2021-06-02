package jafari.alireza.contacts.feature.details



import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import jafari.alireza.contacts.feature.base.BaseViewModel
import jafari.alireza.contacts.model.repository.details.DetailsRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    private val detailsRepository: DetailsRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {


}

