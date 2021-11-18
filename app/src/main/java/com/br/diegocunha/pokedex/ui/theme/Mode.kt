package com.br.diegocunha.pokedex.ui.theme

import androidx.compose.runtime.Stable

//provides theme for dark/light mode
@Stable
interface Mode<T> {
    val lightMode: T
    val darkMode: T
}