package com.msa.petsearch.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.msa.petsearch.activity.util.deployFullScreenSettings
import com.msa.petsearch.commoncompose.theme.ApplicationTheme

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            deployFullScreenSettings(isSystemInDarkTheme())
            ApplicationTheme(isSystemInDarkTheme()) {
                MainScreen()
            }
        }
    }
}
