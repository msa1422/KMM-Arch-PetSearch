package com.msa.petsearch.petdetail.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.msa.petsearch.commonres.R

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun ImagePager(
    images: List<String>,
    onImageClick: (index: Int) -> Unit,
    userScrollEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val placeholder = rememberAsyncImagePainter(R.drawable.ic_bg_paw_print_loading)
    val errorImage = rememberAsyncImagePainter(R.drawable.ic_bg_paw_print_loaded)

    val colorFilter = remember {
        ColorFilter.colorMatrix(
            ColorMatrix().apply { setToSaturation(0.38F) }
        )
    }

    HorizontalPager(
        count = images.size,
        state = pagerState,
        userScrollEnabled = userScrollEnabled,
        modifier = modifier
    ) { index ->
        if (images.size > index) { // This condition should always be true
            Image(
                url = images[index],
                colorFilter = colorFilter,
                placeholder = placeholder,
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
    url: String,
    colorFilter: ColorFilter,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    error: Painter? = null
) {
    val context = LocalContext.current
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(50)
            .build()
    }

    AsyncImage(
        model = imageRequest,
        placeholder = placeholder,
        error = error,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        contentDescription = "",
        colorFilter = colorFilter,
        modifier = modifier
    )
}
