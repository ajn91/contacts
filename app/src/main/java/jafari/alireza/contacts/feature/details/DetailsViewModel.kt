package jafari.alireza.contacts.feature.details



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import jafari.alireza.contacts.feature.base.BaseViewModel
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.details.DetailsModel
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.details.DetailsRepository
import jafari.alireza.contacts.utils.DetailsParams
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val detailsRepository: DetailsRepository,
    contactRepository: ContactRepository
) : BaseViewModel(contactRepository) {

    private val _id = savedStateHandle.getLiveData<Long>(DetailsParams.ID_NAME)
    val detailsLive: LiveData<Resource<DetailsModel?>> = _id.switchMap { id ->
        if (id == null)
            MutableLiveData(Resource.error("Missing ID"))
        else
            detailsRepository.getDetails(id)


    }

}

