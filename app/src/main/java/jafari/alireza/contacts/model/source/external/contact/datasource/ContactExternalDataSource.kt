package jafari.alireza.contacts.model.source.external.contact.datasource

import jafari.alireza.contacts.model.source.external.contact.pojo.ContactExternal


interface ContactExternalDataSource {

 fun getContacts(): List<ContactExternal>
}