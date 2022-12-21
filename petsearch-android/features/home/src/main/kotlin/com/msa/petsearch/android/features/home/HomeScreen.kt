package com.msa.petsearch.android.features.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.msa.petsearch.android.common.components.util.disableSplitMotionEvents
import com.msa.petsearch.android.features.home.composable.HomeProgressIndicator
import com.msa.petsearch.android.features.home.composable.LazyPetGrid
import com.msa.petsearch.android.features.home.composable.tabrow.HomeTabRow
import com.msa.petsearch.android.features.home.composable.tabrow.rememberHomeTabRowState
import com.msa.petsearch.android.features.home.util.isLoading
import com.msa.petsearch.android.features.home.util.isNullOrEmpty
import com.msa.petsearch.shared.resources.SharedR
import com.msa.petsearch.shared.resources.uri
import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.contract.store.GetInitialData
import com.msa.petsearch.shared.ui.home.contract.store.NavigateToPetDetail
import com.msa.petsearch.shared.ui.home.contract.store.OnPetTypeTabChanged
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val gridState = rememberLazyGridState()

    val renderState by viewModel.renderState.collectAsState(initial = null)
    val petList = renderState?.petPagingData?.collectAsLazyPagingItems()

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val systemBars = WindowInsets.systemBars.asPaddingValues()
        val statusBarHeight = remember { systemBars.calculateTopPadding() }
        val screenHeight = maxHeight - statusBarHeight
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // TOpBAR ..............................................................................
            HomeScreenTopBar(scrollState = scrollState)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(screenHeight)
            ) {
                // TAB_LAYOUT ..................................................................
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .defaultMinSize(minHeight = 56.dp)
                        .background(color = MaterialTheme.colorScheme.surface)
                ) {
                    com.msa.petsearch.android.common.components.composable.FadeAnimatedVisibility(
                        visible = !renderState?.petTypes.isNullOrEmpty(),
                        durationMillis = 500
                    ) {
                        renderState?.petTypes?.let { petTypes ->
                            val homeTabRowState = rememberHomeTabRowState()
                            HomeTabRow(
                                items = petTypes.map { it.name },
                                state = homeTabRowState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) { index, name ->
                                viewModel.action(OnPetTypeTabChanged(name))
                                coroutineScope.launch {
                                    homeTabRowState.selectedTabIndex = index
                                    gridState.scrollToItem(0) // Reset grid state
                                }
                            }
                        }
                    }
                }

                // TAB_LAYOUT ..................................................................
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15F))
                )

                // LAZY_GRID ...............................................................................
                if (!petList.isNullOrEmpty()) {
                    val nestedScrollConnection = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(
                                available: Offset,
                                source: NestedScrollSource
                            ): Offset {
                                return if (available.y > 0) Offset.Zero else Offset(
                                    x = 0f,
                                    y = -scrollState.dispatchRawDelta(-available.y)
                                )
                            }
                        }
                    }

                    LazyPetGrid(
                        petList = petList!!,
                        state = gridState,
                        progressIndicatorVisibility = petList.isLoading(),
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(nestedScrollConnection)
                            .disableSplitMotionEvents()
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05F))
                    ) { petInfo ->
                        viewModel.action(NavigateToPetDetail(petInfo))
                    }
                }
                // This else statement is a workaround for the issue where
                // paging data item count becomes 0 (momentarily) when navigating to or from the screen
                // and causes to GridScrollState to reset to 0
                // For detailed explanation, see
                // https://stackoverflow.com/a/70520441
                // This issue also causes the collapsing toolbar to get back to extended position
                else {
                    HomeProgressIndicator(
                        animate = petList.isLoading(),
                        text = stringResource(id = SharedR.strings.loading.resourceId),
                        modifier = Modifier
                            .padding(/*top = innerPadding.calculateTopPadding(), */bottom = 48.dp)
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        if (renderState?.petTypes.isNullOrEmpty()) {
            viewModel.action(GetInitialData)
        }
    }
}

@Composable
private fun HomeScreenTopBar(scrollState: ScrollState, modifier: Modifier = Modifier) {
    val collapsedFractionAlpha by remember(scrollState.value) {
        derivedStateOf { 1f - scrollState.value / scrollState.maxValue.toFloat() }
    }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .windowInsetsPadding(TopAppBarDefaults.windowInsets)
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(minHeight = 64.dp)
            .graphicsLayer { alpha = collapsedFractionAlpha }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable { }
                .padding(start = 20.dp, top = 8.dp, end = 24.dp, bottom = 16.dp)
                .wrapContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentSize()
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = SharedR.assets.near_me.uri
                    ),
                    contentDescription = "Location icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "New York City",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 6.dp, end = 2.dp)
                )
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = SharedR.assets.arrow_drop_down.uri
                    ),
                    contentDescription = "Location icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = "20 W 34th St., New York, United States",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.62F),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 4.dp, top = 2.dp)
            )
        }
    }
}
