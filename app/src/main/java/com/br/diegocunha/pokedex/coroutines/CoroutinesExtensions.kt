package com.br.diegocunha.pokedex.coroutines

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext

/**
 * same use case as doSuspend but returning a value
 * @return the value ret
 */
suspend inline fun <reified T, R> T.doSuspendReturn(
    coroutineContext: CoroutineContext? = null,
    crossinline block: T.() -> R
): R {
    return if (coroutineContext != null) {
        withContext(coroutineContext) {
            block()
        }
    } else {
        block()
    }
}