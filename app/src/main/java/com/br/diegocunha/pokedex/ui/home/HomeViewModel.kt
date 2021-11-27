package com.br.diegocunha.pokedex.ui.home

import androidx.annotation.Keep
import com.br.diegocunha.pokedex.coroutines.DispatchersProvider
import com.br.diegocunha.pokedex.datasource.model.Pokemon
import com.br.diegocunha.pokedex.datasource.repository.PokemonRepository
import com.br.diegocunha.pokedex.ui.state.GetState
import com.br.diegocunha.pokedex.ui.state.refreshState
import com.br.diegocunha.pokedex.ui.viewmodel.StateViewModel

class HomeViewModel(
    dispatchersProvider: DispatchersProvider,
    private val repository: PokemonRepository
) : StateViewModel<HomeViewData>(dispatchersProvider) {

    private lateinit var lastRequestedValue: HomeViewData

    override suspend fun fetch(): GetState<HomeViewData> {
        return repository.loadPokemons().mapSaveResult()
    }

    fun loadMore() {
        launchIO {
            _stateFlow.refreshState(
                onStart = { lastRequestedValue.copy(paginationLoading = true) },
                fetch = { loadNextPage() },
                onFailure = {}
            )
        }
    }


    private suspend fun loadNextPage(): GetState<HomeViewData> {
        return repository.loadNextPage().mapSaveResult()
    }

    private fun GetState<List<Pokemon>>.mapSaveResult(): GetState<HomeViewData> {
        return map { HomeViewData(items = it.orEmpty()) }
            .onSuccess<HomeViewData> {
                lastRequestedValue = it
            }
    }
}

@Keep
data class HomeViewData(
    val paginationLoading: Boolean = false,
    val items: List<Pokemon>
)