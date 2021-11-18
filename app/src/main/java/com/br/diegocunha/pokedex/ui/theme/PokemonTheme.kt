package com.br.diegocunha.pokedex.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.br.diegocunha.pokedex.ui.theme.PokeDexColors
import com.br.diegocunha.pokedex.ui.theme.PokeDexTypography
import com.br.diegocunha.pokedex.ui.theme.LocalPokeDexColors
import com.br.diegocunha.pokedex.ui.theme.LocalPokeDexTypography

object PokemonTheme {

    //colors to be used at theme
    val colors: PokeDexColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPokeDexColors.current

    val PokeDexColors.material: Colors
        get() = Colors(
            primary = context.primary,
            primaryVariant = context.primaryActive,
            secondary = base.secondary,
            secondaryVariant = base.secondaryActive,
            background = base.primary,
            surface = base.secondary,
            error = element.onError,
            onPrimary = element.negative,
            onSecondary = element.primary,
            onBackground = element.primary,
            onSurface = element.primary,
            onError = element.onError,
            isLight = isLight
        )

    //typography used in theme
    val typography: PokeDexTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalPokeDexTypography.current

    val PokeDexTypography.material: Typography
        get() = Typography(
        h1 = h2,
        h2 = h3,
        h3 = h3,
        h4 = h3,
        h5 = h3,
        h6 = h3,
        subtitle1 = p3,
        subtitle2 = p4,
        body1 = p3,
        body2 = p5,
        button = u1,
        caption = p6,
        overline = p3
    )

    /**
     * Proxy to [MaterialTheme]
     */
    val shapes: Shapes
        @Composable
        get() = MaterialTheme.shapes
}