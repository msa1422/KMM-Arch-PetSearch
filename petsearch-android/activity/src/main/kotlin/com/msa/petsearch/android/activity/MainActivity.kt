package com.msa.petsearch.android.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.msa.petsearch.android.activity.util.setFullScreenContent

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenContent {
            MainContent()
        }
    }
}
