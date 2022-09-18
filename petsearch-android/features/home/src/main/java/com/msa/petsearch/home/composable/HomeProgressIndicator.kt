package com.msa.petsearch.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.petsearch.commoncompose.composable.FadeAnimatedVisibility

@Composable
internal fun HomeProgressIndicator(
    animate: Boolean,
    modifier: Modifier = Modifier,
    text: String = ""
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
            text = text.uppercase(),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.38F),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 24.dp)
        )
    }
}
