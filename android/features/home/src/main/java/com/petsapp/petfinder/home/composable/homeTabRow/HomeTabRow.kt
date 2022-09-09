package com.petsapp.petfinder.home.composable.homeTabRow

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.*

@Composable
internal fun HomeTabRow(
    items: List<String>,
    modifier: Modifier = Modifier,
    state: HomeTabRowState = rememberHomeTabRowState(),
    onTabItemClick: (index: Int, name: String) -> Unit = { _, _ -> }
) {

    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(items.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    CustomScrollableTabRow(
        selectedTabIndex = state.selectedTabIndex,
        edgePadding = 8.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.textWidthTabIndicatorOffset(
                    currentTabPosition = tabPositions[state.selectedTabIndex],
                    tabWidth = tabWidths[state.selectedTabIndex]
                )
            )
        },
        divider = {},
        modifier = modifier
    ) {
        items.forEachIndexed { index, petType ->

            val selected = index == state.selectedTabIndex

            Tab(
                selected = selected,
                onClick = { onTabItemClick.invoke(index, petType) },
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.height(56.dp),
                text = @Composable {
                    Text(
                        text = petType.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (selected) Black else SemiBold,
                        fontSize = if (selected) 16.sp else 14.sp,
                        letterSpacing = TextUnit(if (selected) 0.009f else 0.015F, TextUnitType.Em),
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                }
            )

        }
    }

}


// A modified version of the article
// @see https://medium.com/@sukhdip_sandhu/jetpack-compose-scrollabletabrow-indicator-matches-width-of-text-e79c0e5826fe
internal fun Modifier.textWidthTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "textWidthTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.TopStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
        .clip(MaterialTheme.shapes.medium)
}