package com.br.diegocunha.pokedex.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    androidx.compose.material.Button(
        onClick = {
            if (!loading) {
                onClick()
            }
        },
        modifier = modifier,
        enabled = enabled,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = { ButtonLoadingContent(content = content, colors = colors, loading = loading) }
    )
}

/**
 * Used to swap between the regular content and a loading state.
 *
 * @param loading display an circular indicator to indicate a load state
 * @param content display this content when loading is false
 */
@Composable
private fun ButtonLoadingContent(
    colors: ButtonColors,
    loading: Boolean = false,
    content: @Composable RowScope.() -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        var loadingAlphaState by remember { mutableStateOf(1f) }

        loadingAlphaState = if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp), colors.contentColor(
                    enabled = loading
                ).value
            )
            0f
        } else {
            1f
        }

        val alpha: Float by animateFloatAsState(loadingAlphaState)

        Row(
            modifier = Modifier
                .alpha(alpha)
                .wrapContentWidth(),
            content =  content
        )
    }
}