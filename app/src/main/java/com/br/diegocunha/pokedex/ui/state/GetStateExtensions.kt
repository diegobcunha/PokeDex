package com.br.diegocunha.pokedex.ui.state

/**
 * Converts a result to a GetState
 */
fun <T> Result<T>.toGetState(): GetState<T> {
    return fold(
        onSuccess = { GetState.success(it) },
        onFailure = { GetState.failure(it) }
    )
}