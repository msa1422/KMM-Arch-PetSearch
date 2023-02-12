package com.msa.petsearch.android.features.petdetail

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.exitUntilCollapsedScrollBehavior
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msa.petsearch.android.features.petdetail.composable.CollapsingTopAppBar
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.resources.SharedR
import com.msa.petsearch.shared.ui.petdetail.PetDetailViewModel2

@Composable
internal fun PetDetailScreen2(
    viewModel: PetDetailViewModel2,
    modifier: Modifier = Modifier
) {
    val petInfo by viewModel.petInfo.collectAsStateWithLifecycle()
    val scrollBehavior = exitUntilCollapsedScrollBehavior(snapAnimationSpec = null)

    Scaffold(
        topBar = { TopBar(petInfo = petInfo, scrollBehavior = scrollBehavior) },
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
            fullDescription(petInfo)

            tags(petInfo)

            attributes(petInfo)
        }
    }
}

@Composable
private fun TopBar(
    petInfo: PetInfo?,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val backPressedDispatcher =
            LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

        CollapsingTopAppBar(
            title = petInfo?.name ?: "",
            subtitle = petInfo?.shortDescription ?: "",
            pagerImages = petInfo?.photos ?: emptyList(),
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
}

@Suppress("MagicNumber")
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

private fun LazyGridScope.fullDescription(petInfo: PetInfo?) {
    petInfo?.description
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
}

private fun LazyGridScope.tags(petInfo: PetInfo?) {
    petInfo?.tags
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
}

private fun LazyGridScope.attributes(petInfo: PetInfo?) {
    petInfo?.attributes
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
