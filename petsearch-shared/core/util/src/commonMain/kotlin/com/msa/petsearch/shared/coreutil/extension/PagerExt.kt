@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.msa.petsearch.shared.coreutil.extension

import com.kuuurt.paging.multiplatform.Pager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

expect inline fun <reified K : Any, V : Any> Pager<K, V>.loadNextPage()

expect inline fun <reified K : Any, V : Any> Pager<K, V>.loadPreviousPage()

expect inline fun <reified K : Any, V : Any> Pager<K, V>.refreshPager()
