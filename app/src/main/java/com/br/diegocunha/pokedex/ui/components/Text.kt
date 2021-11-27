package com.br.diegocunha.pokedex.ui.components

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme

@Composable
fun StyledText(
    textStyle: TextStyle,
    alpha: Float = LocalContentAlpha.current,
    contentColor: Color = PokemonTheme.colors.text.primary,
    content: @Composable (() -> Unit)
) {
    CompositionLocalProvider(
        LocalContentAlpha provides alpha,
        LocalContentColor provides contentColor,
    ) {
        ProvideTextStyle(textStyle, content)
    }
}