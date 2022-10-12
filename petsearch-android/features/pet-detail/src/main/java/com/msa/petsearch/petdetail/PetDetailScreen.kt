package com.msa.petsearch.petdetail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.msa.petsearch.commoncompose.util.collectAsStateWithLifecycle
import com.msa.petsearch.petdetail.composable.CollapsingTopAppBar
import com.msa.petsearch.shared.domain.petdetailuicontract.PetDetailViewModel
import com.msa.petsearch.shared.resources.SharedR

@Composable
internal fun PetDetailScreen(
    viewModel: PetDetailViewModel,
    modifier: Modifier = Modifier
) {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val state by viewModel.observeState().collectAsStateWithLifecycle(initialValue = null)

    val scrollBehavior = exitUntilCollapsedScrollBehavior(snapAnimationSpec = null)

    Scaffold(
        topBar = {
            Column {
                CollapsingTopAppBar(
                    title = state?.petInfo?.name ?: "",
                    subtitle = state?.petInfo?.shortDescription ?: "",
                    pagerImages = state?.petInfo?.photos ?: emptyList(),
                    onPagerImageClick = {
                        // Navigate to full screen image pager
                    },
                    onBackPressed = { backPressedDispatcher?.onBackPressed() },
                    scrollBehavior = scrollBehavior,
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
                )

                // Divider..........................................................................
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.05F)
                        )
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            // FULL_DESCRIPTION ....................................................................
            state?.petInfo?.description
                ?.takeIf { it.isNotBlank() }
                ?.let {
                    item(span = { GridItemSpan(2) }) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 16.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }
                }

            // TAGS ................................................................................
            state?.petInfo?.tags
                ?.takeIf { it.isNotEmpty() }
                ?.let { tags ->
                    sectionTitle(SharedR.strings.characteristics.resourceId)

                    items(items = tags) { tag ->
                        Text(
                            text = "• $tag",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 24.dp, vertical = 0.dp)
                        )
                    }
                }

            // OTHER_INFO ..........................................................................
            state?.petInfo?.attributes
                ?.let { attr ->
                    sectionTitle(SharedR.strings.attributes.resourceId)

                    val attrMap = hashMapOf<Int, Boolean>().apply {
                        this[SharedR.strings.declawed.resourceId] = attr.declawed
                        this[SharedR.strings.spay_neuter.resourceId] = attr.spayedNeutered
                        this[SharedR.strings.spacial_needs.resourceId] = attr.specialNeeds
                        this[SharedR.strings.house_trained.resourceId] = attr.houseTrained
                        this[SharedR.strings.vaccinated.resourceId] = attr.shotsCurrent
                    }

                    items(items = attrMap.keys.toList()) { key ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 24.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = stringResource(id = key).uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.62F),
                                modifier = Modifier
                            )

                            Text(
                                text = stringResource(
                                    id = if (attrMap[key] == true) {
                                        SharedR.strings.yes.resourceId
                                    } else {
                                        SharedR.strings.no.resourceId
                                    }
                                ),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                            )
                        }
                    }
                }
        }
    }
}

private fun LazyGridScope.sectionTitle(resId: Int) {
    item(span = { GridItemSpan(2) }) {
        Text(
            text = stringResource(id = resId).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 12.sp,
            letterSpacing = TextUnit(0.07f, TextUnitType.Em),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.62F),
            modifier = Modifier
                // Padding modifier before width and height modifier
                // behaves similar to layoutMargin in view-based system
                .padding(top = 24.dp, bottom = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = 24.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
        )
    }
}
