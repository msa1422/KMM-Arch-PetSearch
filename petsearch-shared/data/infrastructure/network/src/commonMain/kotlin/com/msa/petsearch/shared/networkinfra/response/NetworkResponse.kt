package com.msa.petsearch.shared.networkinfra.response

@PublishedApi
internal data class NetworkResponse<T>(
    val data: T?,
    val error: Throwable?
) {
    companion object {
        @PublishedApi
        internal inline fun <reified T> from(data: T? = null, error: Throwable? = null) =
            NetworkResponse(data, error)
    }
}



