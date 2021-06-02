package jafari.alireza.contacts.feature.details



import dagger.hilt.android.lifecycle.HiltViewModel
import jafari.alireza.contacts.feature.base.BaseViewModel
import jafari.alireza.contacts.model.repository.list.ListRepository
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject
constructor(
    private val listRepository: ListRepository) : BaseViewModel() {


}

