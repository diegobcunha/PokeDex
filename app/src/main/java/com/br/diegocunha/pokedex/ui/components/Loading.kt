package com.br.diegocunha.pokedex.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun CardLoadingComponent() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .defaultPlaceholder(RectangleShape)
    )
}