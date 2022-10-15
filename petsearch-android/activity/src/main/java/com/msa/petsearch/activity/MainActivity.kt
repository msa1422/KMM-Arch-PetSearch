package com.msa.petsearch.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.msa.petsearch.activity.util.setFullScreenContent
import com.msa.petsearch.commoncompose.theme.ApplicationTheme

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenContent {
            ApplicationTheme {
                MainScreen()
            }
        }
    }
}
