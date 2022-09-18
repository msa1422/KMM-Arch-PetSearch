package com.msa.petsearch.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.msa.petsearch.commonres.R
import com.msa.petsearch.home.util.items
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.resources.SharedR

@Composable
internal fun LazyPetGrid(
    petList: LazyPagingItems<PetInfo>,
    state: LazyGridState,
    progressIndicatorVisibility: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: (PetInfo) -> Unit = {}
) {
    // remember val(s) are here to improve list performance
    // I feel that LazyGrid performance can be improved during a first scroll or fling
    // Even after cleaning up the layout(a lot) and remembering values during a recompose,
    // jank is still present in a Samsung S7 testing device
    // TODO: Add span logic for Tablet screen sizes

    val placeholder = rememberAsyncImagePainter(R.drawable.ic_bg_paw_print_loading)

    val errorImage = rememberAsyncImagePainter(R.drawable.ic_bg_paw_print_loaded)

    // Setting the ColorFilter to make the images pop a bit less. Skip in production
    val colorFilter = remember {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(IMAGE_SATURATION) })
    }

    // Giving fixed height instead of aspectRatio (0.9F, in this case)
    val imageHeight = (LocalConfiguration.current.screenWidthDp / (ASPECT_RATIO * 2)).dp

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = state,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        if (petList.loadState.prepend is LoadState.Loading) {
            item(span = { GridItemSpan(2) }) {
                HomeProgressIndicator(
                    animate = progressIndicatorVisibility,
                    text = stringResource(id = SharedR.strings.loading.resourceId),
                    modifier = Modifier
                        .padding(bottom = 48.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }

        items(items = petList) { info ->
            info?.let {
                PetInfoItem(
                    petInfo = it,
                    imageHeight = imageHeight,
                    placeholderImage = placeholder,
                    errorImage = errorImage,
                    colorFilter = colorFilter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .clickable { onItemClick.invoke(it) }
                )
            }
        }

        if (petList.loadState.refresh is LoadState.Loading ||
            petList.loadState.append is LoadState.Loading
        ) {
            item(span = { GridItemSpan(2) }) {
                HomeProgressIndicator(
                    animate = progressIndicatorVisibility,
                    text = stringResource(id = SharedR.strings.loading.resourceId),
                    modifier = Modifier
                        .padding(bottom = 48.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }
    }
}

private const val IMAGE_SATURATION = 0.38F
private const val ASPECT_RATIO = 0.9F
