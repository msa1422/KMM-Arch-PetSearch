package com.petsapp.petfinder.home.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.petsapp.petfinder.shared.core_res.CoreR

@Composable
internal fun LoadingText(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = CoreR.strings.loading.resourceId).uppercase(),
        style = MaterialTheme.typography.titleLarge,
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38F),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}