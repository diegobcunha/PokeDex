package com.br.diegocunha.pokedex.ui.home

import com.br.diegocunha.pokedex.coroutines.DispatchersProvider
import com.br.diegocunha.pokedex.datasource.api.PokeDexGraphQL
import com.br.diegocunha.pokedex.datasource.model.Pokemon
import com.br.diegocunha.pokedex.ui.state.GetState
import com.br.diegocunha.pokedex.ui.viewmodel.StateViewModel

class HomeViewModel(dispatchersProvider: DispatchersProvider,
private val api: PokeDexGraphQL): StateViewModel<List<Pokemon>>(dispatchersProvider)  {

    override suspend fun fetch(): GetState<List<Pokemon>> {
        return api.getPokemonDetail(0, 10)
    }
}