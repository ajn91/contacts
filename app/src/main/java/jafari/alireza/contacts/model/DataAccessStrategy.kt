package jafari.alireza.contacts.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


fun <T, A> performGetOperation(
    errorMessage: String = "No item available2",
    localFetch: (() -> LiveData<T?>)? = null,
    externalFetch: (suspend () ->A?)? = null,
    saveExternalResult: (suspend (A) -> Unit)? = null,
    mapExternalToModel: (suspend (A?) -> T?)? = null

): LiveData<Resource<T?>> =
    liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading())
            if (externalFetch == null && localFetch == null) {
                emit(Resource.error(errorMessage))
                return@liveData
            }

            if (localFetch != null) {
                try {
                    val source = localFetch.invoke().map {
                        when {
                            it != null -> Resource.success(it)
                            externalFetch == null -> Resource.error<T>(errorMessage)
                            else -> Resource.loading()
                        }
                    }
                    emitSource(source)
                } catch (e: Exception) {
                    Timber.d("performGetOperation: ${e.message}")
                    emit(Resource.error<T>(errorMessage))

                }
            }

            if (externalFetch != null) {
                try {
                    val externalData = externalFetch.invoke()
                    if (externalData != null) {
                        if (saveExternalResult != null)
                            saveExternalResult( externalData)
                        else {
                            val data = mapExternalToModel!!.invoke( externalData)
                            emit(Resource.success(data))
                        }

                    } else
                        emit(Resource.error<T>("error"))

                } catch (e: Exception) {
                    Timber.d("performGetOperation: ${e.message}")
                    emit(Resource.error<T>(errorMessage))

                }
            }
        } catch (e: Exception) {
            Timber.d("performGetOperation: ${e.message}")
            emit(Resource.error<T>(errorMessage))

        }

    }