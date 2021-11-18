package com.br.diegocunha.pokedex.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class SingleShotFlow<T> {

    private val _events = Channel<T>(Channel.BUFFERED)
    val events: Flow<T> = _events.receiveAsFlow()

    suspend fun postEvent(event: T) {
        _events.send(event)
    }
}