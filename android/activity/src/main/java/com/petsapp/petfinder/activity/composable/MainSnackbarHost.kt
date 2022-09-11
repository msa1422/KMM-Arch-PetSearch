package com.petsapp.petfinder.activity.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petsapp.petfinder.common_compose.util.borderBevel

@Composable
internal fun MainSnackbarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { snackbarData: SnackbarData ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .defaultMinSize(minHeight = 72.dp)
                    .borderBevel()
                    .padding(8.dp),
                shape = RoundedCornerShape(2.dp),
                color = MaterialTheme.colorScheme.onBackground,
                tonalElevation = 12.dp,
                shadowElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = snackbarData.visuals.message,
                        color = MaterialTheme.colorScheme.surface,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp, 12.dp, 8.dp, 12.dp)
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    )
                    snackbarData.visuals.actionLabel?.let { action ->
                        Text(
                            text = action.uppercase(),
                            color = MaterialTheme.colorScheme.background,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(16.dp)
                        )
                    }

                }
            }
        }
    )
}

@Composable
internal fun rememberSnackBarHostState() = remember { SnackbarHostState() }
