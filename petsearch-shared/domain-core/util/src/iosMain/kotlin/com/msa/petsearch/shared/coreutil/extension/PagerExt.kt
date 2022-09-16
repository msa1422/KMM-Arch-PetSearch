package com.msa.petsearch.shared.coreutil.extension

import com.kuuurt.paging.multiplatform.Pager

actual inline fun <reified K: Any, V: Any> Pager<K, V>.loadNextPage() {
    loadNext()
}

actual inline fun <reified K: Any, V: Any> Pager<K, V>.loadPreviousPage() {
    loadPrevious()
}

actual inline fun <reified K: Any, V: Any> Pager<K, V>.refreshPager() {
    refresh()
}
