package com.msa.petsearch.shared.coreutil.resource

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val throwable: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }

        /*@Suppress("UNUSED")
        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }*/
    }
}

enum class Status {
    SUCCESS, ERROR //, LOADING
}

/**
 * Generic Mapper for providing any class as a Resource
 */
inline fun <reified T, S> T?.asResource(mapper: ((T) -> S?) = { null }): Resource<S> {
    return when {
        this == Unit -> Resource.success(null)
        this !is Throwable && this != null -> Resource.success(mapper.invoke(this))
        else -> Resource.error(Throwable((this as? Throwable)?.message ?: "Error"), null)
    }
}
