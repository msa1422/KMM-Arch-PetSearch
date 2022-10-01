package com.msa.petsearch.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.commonres.R as commonR

@Composable
internal fun PetInfoItem(
    petInfo: PetInfo,
    imageHeight: Dp,
    colorFilter: ColorFilter,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val context = LocalContext.current
        val painter = rememberAsyncImagePainter(
            model = remember {
                ImageRequest.Builder(context)
                    .data(petInfo.photos.firstOrNull()?.medium)
                    .placeholder(commonR.drawable.ic_bg_paw_print_loading)
                    .error(commonR.drawable.ic_bg_paw_print_loaded)
                    .crossfade(true)
                    .build()
            },
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painter,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            colorFilter = colorFilter,
            contentDescription = "${petInfo.name} image",
            modifier = Modifier.fillMaxWidth().height(imageHeight)
        )

        Text(
            text = petInfo.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, top = 10.dp, end = 16.dp)
        )

        Text(
            text = petInfo.shortDescription,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75F),
            maxLines = 2,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, top = 2.dp, end = 16.dp, bottom = 20.dp)
        )
    }
}
