package com.br.diegocunha.pokedex.ui.components

import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.br.diegocunha.pokedex.ui.theme.GroupStatus
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme

@Composable
@ReadOnlyComposable
fun statusColorFor(backgroundColor: Color): Color {
    return PokemonTheme.colors.status.colorFor(backgroundColor).takeOrElse {
        LocalContentColor.current
    }
}

fun GroupStatus.colorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        positiveBackground -> positive
        alertBackground -> alert
        infoBackground -> info
        negativeBackground -> negative
        else -> Color.Unspecified
    }
}