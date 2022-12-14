package com.msa.petsearch.android.features.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.msa.petsearch.android.features.home.util.items
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.resources.SharedR
import com.msa.petsearch.shared.resources.uri

@Composable
internal fun LazyPetGrid(
    petList: LazyPagingItems<PetInfo>,
    state: LazyGridState,
    progressIndicatorVisibility: Boolean,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onItemClick: (PetInfo) -> Unit = {}
) {
    // remember val(s) are here to improve list performance
    // I feel that LazyGrid performance can be improved during a first scroll or fling
    // Even after cleaning up the layout(a lot) and remembering values during a recompose,
    // jank is still present in a Samsung S7 testing device

    // Setting the ColorFilter to make the images pop a bit less. Skip in production
    val colorFilter = remember {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(IMAGE_SATURATION) })
    }

    // Giving fixed height instead of aspectRatio (0.9F, in this case)
    val imageHeight = (LocalConfiguration.current.screenWidthDp / (ASPECT_RATIO * 2)).dp

    val placeholder = rememberAsyncImagePainter(
        model = SharedR.assets.bg_paw_print_loading.uri
    )
    val error = rememberAsyncImagePainter(
        model = SharedR.assets.bg_paw_print_loaded.uri
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = state,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = contentPadding,
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
                    colorFilter = colorFilter,
                    placeholder = placeholder,
                    error = error,
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
