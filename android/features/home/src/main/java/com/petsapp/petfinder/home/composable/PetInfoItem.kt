package com.petsapp.petfinder.home.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.petsapp.petfinder.shared.coreentity.petinfo.PetInfo

@Composable
internal fun PetInfoItem(
    petInfo: PetInfo,
    imageHeight: Dp,
    placeholderImage: Painter,
    errorImage: Painter,
    colorFilter: ColorFilter,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val context = LocalContext.current

        // Remember so that a little amount of time can be saved during a recomposition
        // little amount of time -> Probably just a negligible amount of time
        val imageRequest = remember {
            ImageRequest.Builder(context)
                .data(petInfo.photos.firstOrNull()?.medium)
                .crossfade(true)
                .build()
        }

        AsyncImage(
            model = imageRequest,
            placeholder = placeholderImage,
            error = errorImage,
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
