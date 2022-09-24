package com.msa.petsearch.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.msa.petsearch.commoncompose.composable.FadeAnimatedVisibility
import com.msa.petsearch.commonres.R
import com.msa.petsearch.home.composable.tabrow.HomeTabRow
import com.msa.petsearch.home.composable.tabrow.rememberHomeTabRowState
import com.msa.petsearch.shared.coreentity.PetType
import kotlin.math.roundToInt

@Composable
internal fun CollapsibleTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    petTypes: List<PetType>? = null,
    onPetTypeClick: (String) -> Unit = {}
) {
    val homeTabRowState = rememberHomeTabRowState()

    val density = LocalDensity.current

    val systemBars = WindowInsets.systemBars.asPaddingValues()
    val statusBarHeight = remember(systemBars) { systemBars.calculateTopPadding() }
    val expandedHeight = remember(density) { statusBarHeight + ToolbarHeight + TabBarHeight }

    val statusBarHeightPx: Float
    val toolbarHeightPx: Float
    val pinnedHeightPx: Float
    val expandedHeightPx: Float
    val dividerHeightPx: Float

    density.run {
        statusBarHeightPx = remember(density) { statusBarHeight.toPx() }
        toolbarHeightPx = remember(density) { ToolbarHeight.toPx() }
        pinnedHeightPx = remember(density) { toolbarHeightPx + statusBarHeight.toPx() }
        expandedHeightPx = remember(density) { expandedHeight.toPx() }
        dividerHeightPx = remember(density) { DividerHeight.toPx() }
    }

    // Sets the app bar's height offset limit to hide just the bottom title area and keep top title
    // visible when collapsed.
    SideEffect {
        if (scrollBehavior.state.heightOffsetLimit != pinnedHeightPx - expandedHeightPx) {
            scrollBehavior.state.heightOffsetLimit = pinnedHeightPx - expandedHeightPx
        }
    }

    // Set up support for resizing the top app bar when vertically dragging the bar itself.
    val appBarDragModifier = Modifier.draggable(
        orientation = Orientation.Vertical,
        state = rememberDraggableState { delta ->
            if (!scrollBehavior.isPinned) {
                scrollBehavior.state.heightOffset = scrollBehavior.state.heightOffset + delta
            }
        }
    )

    Surface(
        modifier = modifier
            .height(expandedHeight + scrollBehavior.state.heightOffset.div(density.density).dp)
            .then(appBarDragModifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
        ) {
            Layout(
                content = {
                    //
                    // TOOLBAR .....................................................................
                    Box(
                        modifier = Modifier
                            .layoutId(TOOLBAR_ID)
                            .fillMaxWidth()
                            .height(ToolbarHeight)
                            .background(color = MaterialTheme.colorScheme.surface)
                            .graphicsLayer { alpha = 1F - scrollBehavior.state.collapsedFraction }
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
                                    painter = painterResource(id = R.drawable.ic_near_me),
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
                                    imageVector = Icons.Rounded.ArrowDropDown,
                                    contentDescription = "Drop down icon",
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

                    // TAB_LAYOUT ..................................................................
                    Box(
                        modifier = Modifier
                            .layoutId(TAB_BAR_ID)
                            .fillMaxWidth()
                            .height(TabBarHeight)
                            .background(color = MaterialTheme.colorScheme.surface)
                    ) {
                        FadeAnimatedVisibility(
                            visible = !petTypes.isNullOrEmpty(),
                            durationMillis = 500
                        ) {
                            petTypes?.let { petTypes ->
                                HomeTabRow(
                                    items = petTypes.map { it.name },
                                    state = homeTabRowState,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                ) { index, name ->
                                    homeTabRowState.selectedTabIndex = index
                                    onPetTypeClick(name)
                                }
                            }
                        }
                    }

                    // TAB_LAYOUT ..................................................................
                    Box(
                        modifier = Modifier
                            .layoutId(DIVIDER_ID)
                            .fillMaxWidth()
                            .height(DividerHeight)
                            .background(color = MaterialTheme.colorScheme.onBackground
                                .copy(alpha = 0.15F))
                    )
                }
            ) { measurables, constraints ->

                val toolbarPlaceable = measurables.first { it.layoutId == TOOLBAR_ID }
                    .measure(constraints)

                val tabBarPlaceable = measurables.first { it.layoutId == TAB_BAR_ID }
                    .measure(constraints)

                val dividerPlaceable = measurables.first { it.layoutId == DIVIDER_ID }
                    .measure(constraints)

                layout(constraints.maxWidth, expandedHeightPx.roundToInt()) {

                    val heightOffsetPx = scrollBehavior.state.heightOffset / 2

                    val toolbarOffsetPx = statusBarHeightPx + heightOffsetPx
                    toolbarPlaceable.placeRelative(
                        x = 0,
                        y = toolbarOffsetPx.roundToInt()
                    )

                    val tabBarOffsetPx = toolbarOffsetPx + toolbarHeightPx
                    tabBarPlaceable.placeRelative(
                        x = 0,
                        y = tabBarOffsetPx.roundToInt()
                    )

                    val dividerOffsetPx = expandedHeightPx - dividerHeightPx + heightOffsetPx
                    dividerPlaceable.placeRelative(
                        x = 0,
                        y = dividerOffsetPx.roundToInt()
                    )
                }
            }
        }
    }
}

private const val TOOLBAR_ID = 1000
private const val TAB_BAR_ID = 1001
private const val DIVIDER_ID = 1002

private val ToolbarHeight = 64.dp
private val TabBarHeight = 56.dp
private val DividerHeight = 1.dp
