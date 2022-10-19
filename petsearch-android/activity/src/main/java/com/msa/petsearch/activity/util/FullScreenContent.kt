package com.msa.petsearch.activity.util

import android.R.color.transparent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES
import android.view.View.*
import android.view.WindowInsets
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.core.content.res.ResourcesCompat

internal fun ComponentActivity.setFullScreenContent(content: @Composable () -> Unit) {
    setContent {
        WithFullScreenSettings {
            content()
        }
    }
}

@Suppress("deprecation", "InlinedApi")
@Composable
private fun ComponentActivity.WithFullScreenSettings(content: @Composable () -> Unit) {
    window?.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

    when {
        SDK_INT >= VERSION_CODES.R -> {
            window?.setDecorFitsSystemWindows(false)
            window?.insetsController?.let {
                it.hide(WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                it.setSystemBarsAppearance(
                    if (isSystemInDarkTheme()) 0 else APPEARANCE_LIGHT_STATUS_BARS,
                    APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }

        SDK_INT in VERSION_CODES.M..VERSION_CODES.Q -> {
            window?.decorView?.systemUiVisibility =
                SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        if (isSystemInDarkTheme()) 0 else SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Since API 21 and 22 doesn't support LightStatusBar, just add FLAG_TRANSLUCENT_STATUS
        else -> window.addFlags(FLAG_TRANSLUCENT_STATUS)
    }

    window?.statusBarColor = ResourcesCompat.getColor(resources, transparent, theme)

    content()
}
