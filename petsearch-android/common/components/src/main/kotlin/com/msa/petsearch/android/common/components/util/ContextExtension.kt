package com.msa.petsearch.android.common.components.util

import android.content.ContentResolver
import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.annotation.AnyRes

// This method is taken from following source
// @see https://stackoverflow.com/a/72501132
inline fun <reified Activity : ComponentActivity> Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            null
        }
    }
}

@Throws(UnsupportedOperationException::class)
fun Context.buildResourceUri(@AnyRes resId: Int): Uri {
    return Uri.Builder()
        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
        .authority(packageName)
        .path(resId.toString())
        .build()
}
