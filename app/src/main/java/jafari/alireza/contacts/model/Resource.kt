package jafari.alireza.contacts.model

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    val isSuccess: Boolean
        get() = status == Status.SUCCESS

    val isError: Boolean
        get() = status == Status.ERROR

    val isLoading: Boolean
        get() = status == Status.LOADING
    val isNeedNetwork: Boolean
        get() = status == Status.NEED_NETWORK

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        NEED_NETWORK
    }

    companion object {
        fun <T> success(data: T?): Resource<T?> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String? = null, data: T? = null): Resource<T> {

            return Resource(Status.ERROR, data, message)
        }

        fun <T> needNetwork(message: String): Resource<T> {
            return Resource(Status.NEED_NETWORK, null, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}