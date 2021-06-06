package jafari.alireza.contacts.model.source.local.list.datasource

import jafari.alireza.contacts.model.source.external.list.pojo.ContactExternal
import org.jetbrains.annotations.NotNull


interface ListExternalDataSource {

suspend fun getContacts():  List<ContactExternal>
}