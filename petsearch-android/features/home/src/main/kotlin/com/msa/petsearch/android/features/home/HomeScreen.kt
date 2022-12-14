package com.msa.petsearch.android.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.msa.petsearch.android.common.components.util.disableSplitMotionEvents
import com.msa.petsearch.android.features.home.composable.CollapsibleTopBar
import com.msa.petsearch.android.features.home.composable.HomeProgressIndicator
import com.msa.petsearch.android.features.home.composable.LazyPetGrid
import com.msa.petsearch.android.features.home.util.isLoading
import com.msa.petsearch.android.features.home.util.isNullOrEmpty
import com.msa.petsearch.shared.resources.SharedR
import com.msa.petsearch.shared.ui.home.HomeViewModel
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
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

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(Unit) {
        if (renderState?.petTypes.isNullOrEmpty()) {
            viewModel::action.invoke(HomeAction.GetPetTypes)
        }
    }

    Scaffold(
        topBar = {
            CollapsibleTopBar(
                scrollBehavior = scrollBehavior,
                petTypes = renderState?.petTypes,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.surface)
            ) { petTypeName ->
                viewModel::action.invoke(HomeAction.OnPetTypeTabSelected(petTypeName))
                coroutineScope.launch {
                    gridState.scrollToItem(0) // Reset grid state
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        // LAZY_GRID ...............................................................................
        if (!petList.isNullOrEmpty()) {
            LazyPetGrid(
                petList = petList!!,
                state = gridState,
                progressIndicatorVisibility = petList.isLoading(),
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .disableSplitMotionEvents()
                    .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05F))
            ) { petInfo ->
                viewModel::action.invoke(HomeAction.NavigateToPetDetail(petInfo))
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
                    .padding(top = innerPadding.calculateTopPadding(), bottom = 48.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}
