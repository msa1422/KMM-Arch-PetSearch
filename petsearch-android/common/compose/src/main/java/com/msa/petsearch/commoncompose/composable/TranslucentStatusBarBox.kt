package com.msa.petsearch.commoncompose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TranslucentStatusBarBox(
    modifier: Modifier,
    contentAlignment: Alignment = Alignment.TopCenter,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
    ) {
        content()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.38F))
        ) {
            Spacer(modifier = Modifier.statusBarsPadding())
        }
    }
}
