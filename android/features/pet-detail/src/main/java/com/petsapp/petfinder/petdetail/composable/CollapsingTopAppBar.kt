package com.petsapp.petfinder.petdetail.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.petsapp.petfinder.petdetail.composable.ChildrenId.BACK_BUTTON_ID
import com.petsapp.petfinder.petdetail.composable.ChildrenId.PAGER_ID
import com.petsapp.petfinder.petdetail.composable.ChildrenId.TITLE_BG_ID
import com.petsapp.petfinder.petdetail.composable.ChildrenId.TITLE_ID
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollapsingTopAppBar(
    title: String,
    subtitle: String,
    pagerImages: List<String>,
    onPagerImageClick: (index: Int) -> Unit,
    onBackPressed: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val statusBarPadding = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()
    val expandedHeight = screenWidth + PinnedHeight + statusBarPadding
    val pinnedHeight = PinnedHeight + statusBarPadding

    val statusBarPaddingPx: Float
    val pinnedHeightPx: Float
    val expandedHeightPx: Float
    val buttonSizePx: Int

    LocalDensity.current.run {
        statusBarPaddingPx = statusBarPadding.toPx()
        expandedHeightPx = expandedHeight.toPx()
        pinnedHeightPx = pinnedHeight.toPx()
        buttonSizePx = ButtonSize.roundToPx()
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
            .height(expandedHeight +
                    scrollBehavior.state.heightOffset.div(LocalDensity.current.density).dp
            )
            .then(appBarDragModifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
        ) {
            Layout(
                content = {
                    // BACK_BUTTON..................................................................
                    CompositionLocalProvider(
                        LocalContentColor provides MaterialTheme.colorScheme.onBackground,
                        content = {
                            Icon(
                                contentDescription = "Back Button",
                                imageVector = Icons.Rounded.ArrowBack,
                                modifier = Modifier
                                    .layoutId(BACK_BUTTON_ID)
                                    .size(ButtonSize)
                                    .padding(top = 4.dp, end = 4.dp, bottom = 4.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface
                                            .copy(alpha = 0.62F),
                                        shape = RoundedCornerShape(
                                            topEnd = 24.dp,
                                            bottomEnd = 24.dp
                                        )
                                    )
                                    .clickable { onBackPressed.invoke() }
                                    .padding(all = 18.dp)
                            )
                        }
                    )

                    // IMAGE_PAGER .................................................................
                    ImagePager(
                        images = pagerImages,
                        onImageClick = onPagerImageClick,
                        userScrollEnabled = scrollBehavior.state.collapsedFraction < 0.5F,
                        modifier = Modifier
                            .layoutId(PAGER_ID)
                            .fillMaxWidth()
                            .height(screenWidth + statusBarPadding)
                            .graphicsLayer {
                                alpha = 1F - scrollBehavior.state.collapsedFraction
                            }
                    )

                    // TITLE........................................................................
                    CompositionLocalProvider(
                        LocalContentColor provides MaterialTheme.colorScheme.onBackground,
                        content = {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .layoutId(TITLE_ID)
                                    .fillMaxWidth()
                                    .height(PinnedHeight)
                            ) {
                                ProvideTextStyle(
                                    value = MaterialTheme.typography.titleMedium
                                    // For text shadow
                                    /*.copy(
                                        shadow = Shadow(
                                            color = MaterialTheme.colorScheme.background.copy(alpha = 0.5F),
                                            offset = Offset(x = 0F, y = 2F),
                                            blurRadius = 8F
                                        )
                                    )*/
                                ) {
                                    Text(
                                        text = title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                                ProvideTextStyle(value = MaterialTheme.typography.bodySmall) {
                                    Text(
                                        text = subtitle.replace("\n", ", "),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    )

                    // Title Background
                    Box(
                        modifier = Modifier
                            .layoutId(TITLE_BG_ID)
                            .fillMaxWidth()
                            .height(PinnedHeight)
                            .background(color = MaterialTheme.colorScheme.surface)
                    )
                }
            ) { measurables, constraints ->
                val pagerPlaceable =
                    measurables.first { it.layoutId == PAGER_ID }
                        .measure(constraints)

                val backButtonPlaceable =
                    measurables.first { it.layoutId == BACK_BUTTON_ID }
                        .measure(constraints.copy(minWidth = 0))

                val maxTitleWidth = if (constraints.maxWidth == Constraints.Infinity) {
                    constraints.maxWidth
                }
                else {
                    (constraints.maxWidth - backButtonPlaceable.width).coerceAtLeast(0)
                }

                val titlePlaceable =
                    measurables.first { it.layoutId == TITLE_ID }
                        .measure(constraints.copy(maxWidth = maxTitleWidth))

                val titleBgPlaceable =
                    measurables.first { it.layoutId == TITLE_BG_ID }
                        .measure(constraints)

                layout(constraints.maxWidth, expandedHeightPx.roundToInt()) {
                    val titleWidthOffset =
                        (buttonSizePx * scrollBehavior.state.collapsedFraction +
                                (24.dp.roundToPx() * (1F - scrollBehavior.state.collapsedFraction))
                                ).roundToInt()

                    val titleHeightOffset =
                        (expandedHeightPx - pinnedHeightPx + statusBarPaddingPx +
                                scrollBehavior.state.heightOffset / 2
                                ).roundToInt()

                    val backButtonHeightOffset = statusBarPaddingPx - scrollBehavior.state.heightOffset / 2

                    // Pager
                    pagerPlaceable.placeRelative(
                        x = 0,
                        y = -(scrollBehavior.state.heightOffset/2).roundToInt()
                    )

                    // Title Bg Gradient
                    titleBgPlaceable.placeRelative(x = 0, y = titleHeightOffset)

                    // Back Button
                    backButtonPlaceable.placeRelative(x = 0, y = backButtonHeightOffset.roundToInt())

                    // Title
                    titlePlaceable.placeRelative(x = titleWidthOffset, y = titleHeightOffset)
                }
            }
        }
    }
}

private object ChildrenId {
    const val BACK_BUTTON_ID = 1001
    const val PAGER_ID = 1002
    const val PAGER_INDICATOR_ID = 1003
    const val TITLE_ID = 1004
    const val TITLE_BG_ID = 1005
}

private val PinnedHeight = 68.dp
private val ButtonSize = PinnedHeight
