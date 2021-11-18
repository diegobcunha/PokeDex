package com.br.diegocunha.pokedex.ui.viewmodel

import com.br.diegocunha.pokedex.coroutines.DispatchersProvider
import com.br.diegocunha.pokedex.coroutines.retryState
import com.br.diegocunha.pokedex.ui.state.GetState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StateViewModel<T>(dispatcherProvider: DispatchersProvider) :
    CoroutineViewModel(dispatcherProvider) {

    protected val _stateFlow: MutableStateFlow<GetState<T>> by lazy {
        MutableStateFlow<GetState<T>>(GetState.initial()).apply {
            launchIO {
                emit(fetch())
            }
        }
    }

    val stateFlow: StateFlow<GetState<T>> by lazy { _stateFlow.asStateFlow() }

    abstract suspend fun fetch(): GetState<T>

    fun retry() {
        launchIO {
            _stateFlow.retryState(::fetch)
        }
    }
}