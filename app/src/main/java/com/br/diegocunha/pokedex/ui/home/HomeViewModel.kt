package com.br.diegocunha.pokedex.ui.home

import com.br.diegocunha.pokedex.coroutines.DispatchersProvider
import com.br.diegocunha.pokedex.datasource.api.PokeDexGraphQL
import com.br.diegocunha.pokedex.datasource.model.PokemonResult
import com.br.diegocunha.pokedex.ui.state.GetState
import com.br.diegocunha.pokedex.ui.viewmodel.StateViewModel

class HomeViewModel(dispatchersProvider: DispatchersProvider,
private val api: PokeDexGraphQL): StateViewModel<List<PokemonResult>>(dispatchersProvider)  {

    override suspend fun fetch(): GetState<List<PokemonResult>> {
        return api.main()
    }
}