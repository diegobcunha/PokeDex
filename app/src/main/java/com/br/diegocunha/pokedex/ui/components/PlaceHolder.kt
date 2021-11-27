package com.br.diegocunha.pokedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import androidx.compose.material.Divider
import androidx.compose.ui.unit.dp


private const val DEFAULT_PLACEHOLDER_ROWS = 20

/**
 * Show an entire list of rows placeholder
 */
@Composable
fun PlaceholderFullScreen(placeholder: @Composable () -> Unit) {
    Column {
        for (index in 0..DEFAULT_PLACEHOLDER_ROWS) {
            if (index > 0) {
                Divider(
                    thickness = 16.dp
                )
            }
            placeholder()
        }
    }
}

@Composable
fun Modifier.defaultPlaceholder(shape: Shape = RoundedCornerShape(percent = 14)) = clip(shape)
    .background(PokemonTheme.colors.element.negative)
    .placeholder(
        visible = true,
        color = PokemonTheme.colors.base.primary,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = PokemonTheme.colors.base.secondary,
            customShimmerAnimation()
        )
    )