package com.br.diegocunha.pokedex.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.diegocunha.pokedex.coroutines.DispatchersProvider
import com.br.diegocunha.pokedex.coroutines.ScopedContextDispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch

open class CoroutineViewModel(
    protected val dispatcherProvider: DispatchersProvider
): ViewModel() {

    protected val scopedContextProvider =
        ScopedContextDispatchers(
            viewModelScope,
            dispatcherProvider
        )

    fun io(): CoroutineContext = scopedContextProvider.io
    fun main(): CoroutineContext = scopedContextProvider.main


    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, start, block)


    fun launchIO(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(io(), start, block)

    fun launchMain(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = launch(main(), start, block)

}