package com.br.diegocunha.pokedex.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

class ScopedContextDispatchers(
    val scope: CoroutineScope,
    private val dispatchersProvider: DispatchersProvider
) {

    val main: CoroutineContext
        get() = scope.coroutineContext + dispatchersProvider.main
    val io: CoroutineContext
        get() = scope.coroutineContext + dispatchersProvider.io
}