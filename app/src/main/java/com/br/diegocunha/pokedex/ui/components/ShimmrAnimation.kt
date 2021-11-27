package com.br.diegocunha.pokedex.ui.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween

/**
 * Custom animation of shimmer to remove delay of replay
 * @param durationMillis: Duration of the animation
 * @param delayMillis: Delay to start/restart animation
 * @param repeatMode: Type of repeat of animation
 */
fun customShimmerAnimation(
    durationMillis: Int = 1700,
    delayMillis: Int = 0,
    repeatMode: RepeatMode = RepeatMode.Restart
): InfiniteRepeatableSpec<Float> = infiniteRepeatable(
    animation = tween(durationMillis = durationMillis, delayMillis = delayMillis),
    repeatMode = repeatMode
)