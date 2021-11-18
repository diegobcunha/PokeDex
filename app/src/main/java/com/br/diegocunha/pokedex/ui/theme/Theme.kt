package com.br.diegocunha.pokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme.material


@Composable
fun PokeDexAppTheme(
    colors: PokeDexColors = defaultColorsBySystem(),
    typography: PokeDexTypography = PokeDexTypography.default,
    content: @Composable() () -> Unit
) {

    CompositionLocalProvider(
        LocalPokeDexColors provides colors,
        LocalPokeDexTypography provides typography,
    ) {
        MaterialTheme(
            colors = PokemonTheme.colors.material,
            typography = PokemonTheme.typography.material,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
private fun defaultColorsBySystem(): PokeDexColors {
    return if(isSystemInDarkTheme()) {
        PokeDexColors.darkMode
    } else {
        PokeDexColors.lightMode
    }
}