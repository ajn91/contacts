package jafari.alireza.contacts.model.source.local.list.datasource

import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal


interface ContactExternalDataSource {

suspend fun getContacts():  List<ContactExternal>
}