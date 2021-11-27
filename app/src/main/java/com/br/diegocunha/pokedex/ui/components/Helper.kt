package com.br.diegocunha.pokedex.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.diegocunha.pokedex.R
import com.br.diegocunha.pokedex.ui.state.GetFailure
import com.br.diegocunha.pokedex.ui.theme.PokemonTheme

@Composable
fun Helper(
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit,
    title: @Composable () -> Unit,
    subtitle: @Composable () -> Unit,
    action: @Composable (() -> Unit)? = null
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                image()
            }

            StyledText(
                // TODO: font Size was changed to 32.dp because h1 size is incorrect.
                // Until we don't visit the typography this size must be overridden
                textStyle = PokemonTheme.typography.h1.copy(fontSize = 32.sp),
                contentColor = PokemonTheme.colors.text.primary,
                alpha = ContentAlpha.high,
                content = title
            )
            Box(modifier = Modifier.padding(horizontal = 24.dp)) {
                StyledText(
                    textStyle = PokemonTheme.typography.p4.copy(textAlign = TextAlign.Center),
                    contentColor = PokemonTheme.colors.text.secondary,
                    alpha = ContentAlpha.medium,
                    content = subtitle
                )
            }
            action?.invoke()

        }
    }
}

@Composable
fun GetFailureHelper(
    modifier: Modifier = Modifier,
    getFailure: GetFailure,
    onRetry: () -> Unit
) {
    Log.e("Helper", null, getFailure.throwable)

    Helper(
        modifier = modifier.fillMaxHeight(),
        image = { FailureIcon() },
        title = { Text(stringResource(id = R.string.resources_error_default_title)) },
        subtitle = { Text(stringResource(id = R.string.templates_error_general)) },
        action = {
            Button(onClick = onRetry, loading = getFailure.retrying) {
                Text(stringResource(id = R.string.resources_try_again))
            }
        }
    )
}

@Composable
private fun FailureIcon() {
    HelperIcon(
        painter = painterResource(id = R.drawable.ic_exclamation),
        backgroundColor = PokemonTheme.colors.context.primary
    )
}

@Composable
fun HelperIcon(
    painter: Painter,
    backgroundColor: Color,
    statusTint: Color = statusColorFor(backgroundColor = backgroundColor)
) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .size(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = statusTint
        )
    }
}