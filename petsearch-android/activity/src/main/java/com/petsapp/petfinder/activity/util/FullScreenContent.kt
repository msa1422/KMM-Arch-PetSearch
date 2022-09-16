package com.petsapp.petfinder.activity.util

import android.R.color.transparent
import android.os.Build
import android.view.View.*
import android.view.WindowInsets
import android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.content.res.ResourcesCompat

internal fun ComponentActivity.setFullScreenContent(content: @Composable () -> Unit) {
    setContent(content = content)
    deployFullScreenSettings()
}

@Suppress("deprecation")
private fun ComponentActivity.deployFullScreenSettings() {
    window?.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window?.setDecorFitsSystemWindows(false)

        window?.insetsController?.let {
            it.hide(WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        window?.decorView?.systemUiVisibility = SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        window?.decorView?.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    window?.statusBarColor = ResourcesCompat.getColor(resources, transparent, theme)
}
