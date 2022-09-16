package com.msa.petsearch.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.petsapp.petfinder.commoncompose.composable.FadeAnimatedVisibility
import com.petsapp.petfinder.shared.coreres.CoreR

@Composable
internal fun HomeProgressIndicator(
    animate: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        FadeAnimatedVisibility(visible = animate) {
            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground,
                trackColor = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
        }

        Text(
            text = stringResource(id = CoreR.strings.loading.resourceId).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38F),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
        )
    }
}
