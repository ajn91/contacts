package jafari.alireza.contacts.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


fun <T, A> performGetOperation(
    errorMessage: String = "No item available2",
    localFetch: (() -> LiveData<T?>)? = null,
    remoteFetch: (suspend () -> Resource<A?>)? = null,
    saveRemoteResult: (suspend (A) -> Unit)? = null,
    mapRemoteToModel: (suspend (A?) -> T?)? = null

): LiveData<Resource<T?>> =
    liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading())
            if (remoteFetch == null && localFetch == null) {
                emit(Resource.error(errorMessage))
                return@liveData
            }

            if (localFetch != null) {
                try {
                    val source = localFetch.invoke().map {
                        if (it != null)
                            Resource.success(it)
                        else
                            if (remoteFetch == null)
                                Resource.error<T?>(errorMessage)
                            else
                                Resource.loading()
                    }
                    emitSource(source)
                } catch (e: Exception) {
                    Timber.d("performGetOperation: ${e.message}")
                    emit(Resource.error<T?>(errorMessage))

                }
            }

            if (remoteFetch != null) {
                try {
                    val responseStatus = remoteFetch.invoke()
                    if (responseStatus.status == Resource.Status.SUCCESS) {
                        if (saveRemoteResult != null)
                            saveRemoteResult(responseStatus.data!!)
                        else {
                            val data = mapRemoteToModel!!.invoke(responseStatus.data)
                            emit(Resource.success(data))
                        }

                    } else if (responseStatus.status == Resource.Status.ERROR)
                        emit(Resource.error(responseStatus.message!!))

                } catch (e: Exception) {
                    Timber.d("performGetOperation: ${e.message}")
                    emit(Resource.error<T?>(errorMessage))

                }
            }
        } catch (e: Exception) {
            Timber.d("performGetOperation: ${e.message}")
            emit(Resource.error<T?>(errorMessage))

        }

    }