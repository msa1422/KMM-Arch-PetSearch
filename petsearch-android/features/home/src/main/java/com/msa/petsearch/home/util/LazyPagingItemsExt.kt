package com.msa.petsearch.home.util

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

internal inline fun <reified T : Any> LazyPagingItems<T>?.isLoading(): Boolean {
    return this == null ||
            this.loadState.refresh is LoadState.Loading ||
            this.loadState.append is LoadState.Loading ||
            this.loadState.prepend is LoadState.Loading
}

internal inline fun <reified T : Any> LazyPagingItems<T>?.isNullOrEmpty(): Boolean {
    return this == null || this.itemCount == 0
}
