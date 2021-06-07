package jafari.alireza.contacts.feature.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import jafari.alireza.contacts.feature.base.BaseViewModel
import jafari.alireza.contacts.model.Resource
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.model.repository.list.ListRepository
import jafari.alireza.contacts.utils.DirectionParamName
import jafari.alireza.contacts.utils.Event
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject
constructor(
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


    init {
        postLoading()
    }

    fun postLoading() {
        _itemsLive.value = Resource.loading()
    }


    fun onItemClick(item: ListModel) {
        _directToPageLive.value = Event(DirectionParamName.DetailsParams(item.contactId))
    }


    fun getItems() {
        if (_itemsLive.value?.isSuccess == true)
            return
        val dataSource =
            listRepository.getContacts()
        _itemsLive.addSource(dataSource) {
            _itemsLive.postValue(it)

        }
    }

    fun setItemLoadedCount(totalItems: Int) {
        _itemLoadedCount.value = totalItems
    }


    override fun onStop() {
        super.onStop()
        _itemLoadedCount.value = 0
    }



}

