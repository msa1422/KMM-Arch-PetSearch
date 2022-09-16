package com.petsapp.petfinder.commoncompose.util

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlin.math.sqrt

fun Modifier.borderBevel(
    width: Dp = 1.dp,
    brush: Brush = Brush
        .verticalGradient(colors = listOf(Color(0x5CDCFD5), Color(0xA081A33))),
    shape: Shape = RoundedCornerShape(2.dp)
) = this.then(
    Modifier.border(width = width, brush = brush, shape = shape)
)

fun Modifier.enableVerticalParallax(listState: LazyListState) = this.then(
    Modifier.graphicsLayer {
        translationY = listState.firstVisibleItemScrollOffset.toFloat() / 10
    }
)

fun Modifier.trblRadialGradientBg(vararg colorStops: Pair<Float, Color>) = this.then(
    Modifier.drawBehind {
        drawRect(
            brush = Brush.radialGradient(
                colorStops = colorStops,
                center = Offset(size.width + 12F, -12F),
                radius = sqrt(size.width * size.width + size.height * size.height) + 64F
            ),
            size = size
        )
    }
)

fun Modifier.tcbcRadialGradientBg(vararg colorStops: Pair<Float, Color>) = this.then(
    Modifier.drawBehind {
        drawRect(
            brush = Brush.radialGradient(
                colorStops = colorStops,
                center = Offset(size.width + 64.dp.value, -64.dp.value),
                radius = sqrt(((size.height) * (size.height)) + ((size.width) * (size.width))) * 1.619F
            ),
            size = size
        )
    }
)

fun Modifier.disableSplitMotionEvents() = this.then(
    pointerInput(Unit) {
        coroutineScope {
            var currentId: Long = -1L
            awaitPointerEventScope {
                while (true) {
                    awaitPointerEvent(PointerEventPass.Initial).changes.forEach { pointerInfo ->
                        when {
                            pointerInfo.pressed && currentId == -1L ->
                                currentId = pointerInfo.id.value
                            pointerInfo.pressed.not() && currentId == pointerInfo.id.value ->
                                currentId = -1
                            pointerInfo.id.value != currentId && currentId != -1L ->
                                pointerInfo.consume()
                            else -> Unit
                        }
                    }
                }
            }
        }
    }
)

fun Modifier.clickableIf(
    predicate: Boolean,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit = {}
) = this.takeIf { predicate }
    ?.then(Modifier.clickable(enabled, onClickLabel, role, onClick))
    ?: this

fun Modifier.ignoreHorizontalParentPadding(horizontal: Dp) = this
    .layout { measurable, constraints ->
        val overriddenWidth = constraints.maxWidth + 2 * horizontal.roundToPx()
        val placeable = measurable.measure(constraints.copy(maxWidth = overriddenWidth))
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
