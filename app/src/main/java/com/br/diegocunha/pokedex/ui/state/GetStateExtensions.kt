package com.br.diegocunha.pokedex.ui.state

import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Converts a result to a GetState
 */
fun <T> Result<T>.toGetState(): GetState<T> {
    return fold(
        onSuccess = { GetState.success(it) },
        onFailure = { GetState.failure(it) }
    )
}

/**
 * Default refresh function used when the get use case are in GetState.Success to apply the
 * following behaviors:
 * - updates the UI about the new refreshing state
 * - apply the default retry policy
 * - notify the view about some errors
 * - refresh the view to the original state on errors
 * - refresh the view to the new state on success
 *
 * @param onStart - returns a new state to indicates the UI the refresh starts. Usually it will be
 * implemented with a simple { it.copy(refreshing = true) }
 * @param fetch - do the request to refresh the content
 * @param onFailure - notifies the view about the error on refresh. As we are
 * update the current state to refreshing
 */
suspend fun <T> MutableStateFlow<GetState<T>>.refreshState(
    onStart: (current: T) -> T,
    fetch: suspend () -> GetState<T>,
    onFailure: suspend (Throwable) -> Unit
) {
    val current = value
    val currentRefreshing = onStart(current.success)
    value = GetState.success(currentRefreshing)
    val fetched = fetch()
    value = if (fetched.currentStatus() == GetStatus.SUCCESS) {
        fetched
    } else {
        onFailure(fetched.failure.throwable)
        current
    }
}

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