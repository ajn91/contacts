package jafari.alireza.contacts.feature.service

import android.content.Context
import android.database.ContentObserver
import android.provider.ContactsContract
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltWorker
class ContactWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters, val contactRepository: ContactRepository
) : Worker(appContext, workerParams) {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    var contactObserver: ContactObserver? = null


    fun observeContact() {
        if (contactObserver != null)
            return
        contactObserver = ContactObserver()
        applicationContext.contentResolver
            .registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, contactObserver!!
            )

    }

    override fun doWork(): Result {
        serviceScope.launch {
            observeContact()
        }
        return Result.success()
    }

    fun updateContacts() {
        serviceScope.launch {
            contactRepository.updateContacts()

        }
    }

    override fun onStopped() {
        super.onStopped()
        if (contactObserver != null) {
            applicationContext.contentResolver
                .unregisterContentObserver(contactObserver!!)
            contactObserver = null
        }
        serviceJob.cancel()


    }

    inner class ContactObserver : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)

            updateContacts()
        }

        override fun deliverSelfNotifications(): Boolean {
            return true
        }
    }


}