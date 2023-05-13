package com.msa.petsearch.android.features.petdetail.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.msa.petsearch.shared.core.entity.petinfo.PetPhoto
import com.msa.petsearch.shared.resources.SharedR

@OptIn(ExperimentalFoundationApi::class)
@Suppress("MagicNumber")
@Composable
internal fun ImagePager(
    images: List<PetPhoto>,
    onImageClick: (index: Int) -> Unit,
    userScrollEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = images::size)
    val errorImage = rememberAsyncImagePainter(
        model = SharedR.images.bg_paw_print_loaded.drawableResId
    )

    val colorFilter = remember {
        ColorFilter.colorMatrix(
            ColorMatrix().apply { setToSaturation(0.38F) }
        )
    }

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = userScrollEnabled,
        modifier = modifier
    ) { index ->
        if (images.size > index) { // This condition should always be true
            Image(
                photo = images[index],
                colorFilter = colorFilter,
                error = errorImage,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onImageClick.invoke(index) }
            )
        }
    }
}

@Composable
private fun Image(
    photo: PetPhoto,
    colorFilter: ColorFilter,
    modifier: Modifier = Modifier,
    error: Painter
) {
    val context = LocalContext.current
    val mainImage = remember {
        ImageRequest.Builder(context)
            .data(photo.large.ifBlank { error })
            .crossfade(false)
            .build()
    }
    val placeholder = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(photo.medium.ifBlank { error })
            .crossfade(false)
            .build()
    )

    AsyncImage(
        model = mainImage,
        placeholder = placeholder,
        error = error,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        contentDescription = "",
        colorFilter = colorFilter,
        modifier = modifier
    )
}
