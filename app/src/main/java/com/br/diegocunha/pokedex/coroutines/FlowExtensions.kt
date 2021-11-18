package com.br.diegocunha.pokedex.coroutines

import com.br.diegocunha.pokedex.ui.state.GetState
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Default refresh function used when the get use case are in GetState.Failure to apply the
 * following behaviors:
 * - updates the ui about the new retrying state of failure
 * - try the request again
 * - apply the default retry polocy
 */
suspend fun <T> MutableStateFlow<GetState<T>>.retryState(
    fetch: suspend () -> GetState<T>
) {
    val failureRetrying = value.failure.copy(retrying = true)
    value = GetState.failure(failureRetrying)
    value = fetch()
}