package com.br.diegocunha.pokedex.ui.state

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable

/**
 * Default animation to be used for "get use cases" features used to replace 3 states of UI:
 * @param T - the success type of GetState
 * @param state - given state used to determinate the new UI state shown
 * @param initial - Started state of UI it's typically a shimmer animation or some text with instructions
 * @param success - Main state of UI shown after the content was fetched from some possible
 * long task
 * @param failure - Exceptional state of UI shown after some error that blocks UX and should be
 * shown to indicates the user about whats happening
 */
@Composable
fun <T> GetCrossfade(
    state: GetState<T>,
    initial: @Composable (GetInitial) -> Unit,
    failure: @Composable (GetFailure) -> Unit,
    success: @Composable (T) -> Unit,
) {
    Crossfade(targetState = state) { targetState ->
        when (targetState.currentStatus()) {
            GetStatus.INITIAL -> initial(targetState.initial)
            GetStatus.FAILURE -> failure(targetState.failure)
            GetStatus.SUCCESS -> success(targetState.success)
        }
    }
}