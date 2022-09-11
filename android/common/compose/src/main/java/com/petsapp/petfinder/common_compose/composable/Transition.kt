package com.petsapp.petfinder.common_compose.composable

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@Composable
fun FadeAnimatedVisibility(
    visible: Boolean,
    durationMillis: Int = 200,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = 5,
                easing = FastOutSlowInEasing
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = durationMillis,
                delayMillis = 5,
                easing = FastOutSlowInEasing
            )
        ),
        content = content
    )
}

@Composable
fun SlideInAnimatedVisibility(
    visible: Boolean,
    offset: (IntSize) -> IntOffset = { IntOffset(x = 0, y = 32) },
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 5,
                easing = FastOutSlowInEasing
            )
        ) + slideIn(
            animationSpec = tween(
                durationMillis = 500,
                delayMillis = 5,
                easing = FastOutSlowInEasing
            ),
            initialOffset = offset
        ),
        content = content
    )
}
