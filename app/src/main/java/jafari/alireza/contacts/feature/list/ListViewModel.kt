package jafari.alireza.contacts.feature.details


import android.location.Location
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import jafari.alireza.contacts.feature.base.BaseViewModel
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.list.ListRepository
import jafari.alireza.contacts.utils.DirectionParamName
import jafari.alireza.contacts.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class ListViewModel @Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val listRepository: ListRepository,
   contactRepository: ContactRepository
) : BaseViewModel(contactRepository) {

    private var _itemsLive = MediatorLiveData<Resource<List<ListModel>?>>()
    val itemsLive: LiveData<Resource<List<ListModel>?>>
        get() = _itemsLive

    private val _itemLoadedCount = MutableLiveData(0)
    val itemLoadedCount: LiveData<Int>
        get() = _itemLoadedCount



    private val _directToPageLive = MutableLiveData<Event<DirectionParamName?>>()
    val directToPageLive: LiveData<Event<DirectionParamName?>>
        get() = _directToPageLive


    val needInitializeLive = savedStateHandle.getLiveData<Boolean>(LOADING).map {
//        if (it) {
//            postLoading()
//            getItems()
//        }
//        it
    }

    init {
        postLoading()
    }

    fun postLoading() {
        _itemsLive.value =Resource.loading()
    }


    fun onItemClick(item: ListModel) {
        _directToPageLive.value = Event(DirectionParamName.DetailsParams(item.contactId))
    }


    fun getItems() {
        if (_itemsLive.value?.isSuccess == true )
            return
        val dataSource =
            listRepository.getContacts()
        _itemsLive.addSource(dataSource) {
            _itemsLive.postValue(it)
//            if (!it.isLoading)
//                _itemsLive.removeSource(dataSource)
        }
        _itemsLive.addSource(contactChangeLive) {
           updateContacts()
        }
    }
fun updateContacts(){
    viewModelScope.launch(Dispatchers.IO){
        listRepository.updateContacts()
    }
}

    private fun reNewData(location: Location) {
//        viewModelScope.launch(Dispatchers.IO) {
//            generalRepository.clearData()
//        }
//        updateLastUpdateData(location)
    }


    fun setItemLoadedCount(totalItems: Int) {
        _itemLoadedCount.value = totalItems
    }

    fun loadPage() {
        getItems()
    }


    override fun onStop() {
        super.onStop()
        savedStateHandle.set(LOADING, true)
        _itemLoadedCount.value = 0
    }


    companion object {
        const val LOADING: String = "loading"
        const val MAX_DAYS_TO_RENEW_DATA = 3
        const val MAX_DISTANCE_TO_RENEW_DATA = 100f

    }
}

