package jafari.alireza.contacts.feature.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.database.ContentObserver
import android.os.Handler
import android.os.IBinder
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.MutableLiveData
import curtains.OnContentChangedListener
import dagger.hilt.android.AndroidEntryPoint
import jafari.alireza.contacts.feature.base.ContactObserver
import jafari.alireza.contacts.model.repository.contact.ContactRepository
import jafari.alireza.contacts.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ContactService : JobService() {
    @Inject
    lateinit var contactRepository: ContactRepository
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    var contactObserver: ContactObserver? = null



    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        observeContact()
return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        if (contactObserver != null ){
        contentResolver
            .unregisterContentObserver(contactObserver!!)
            contactObserver = null
        }
        return  true
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    protected fun observeContact() {
        if (contactObserver != null)
            return
        contactObserver = ContactObserver()
        contentResolver
            .registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI, true, contactObserver!!
            )

    }
fun  updateContacts(){
    serviceScope.launch {
        contactRepository.updateContacts()

    }
}
    companion object {
        private const val TAG = "MyService"
    }

    inner  class ContactObserver() : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            updateContacts()
        }

        override fun deliverSelfNotifications(): Boolean {
            return true
        }
    }

}