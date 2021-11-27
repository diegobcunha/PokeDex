package com.br.diegocunha.pokedex.datasource.repository

import com.br.diegocunha.pokedex.datasource.api.PokeDexGraphQL
import com.br.diegocunha.pokedex.datasource.model.Pokemon
import com.br.diegocunha.pokedex.ui.state.GetState

class PokemonRepository(private val executor: PokeDexGraphQL) {

    private var currentOffset = 0
    private val listOfPokemons = mutableListOf<Pokemon>()

    suspend fun loadPokemons(): GetState<List<Pokemon>> {
        return executor.getPokemonDetail(currentOffset, LIMIT).map {
            listOfPokemons.addAll(it.orEmpty())
            listOfPokemons
        }.onSuccess<List<Pokemon>> { currentOffset += OFFSET }
    }

    suspend fun loadNextPage(): GetState<List<Pokemon>> {
        return loadPokemons().onSuccess<List<Pokemon>> {
            currentOffset += OFFSET
        }
    }

    suspend fun resetLoad(): GetState<List<Pokemon>> {
        currentOffset = OFFSET
        listOfPokemons.clear()
        return loadPokemons()
    }


    companion object {
        private const val OFFSET = 10
        private const val LIMIT = 10
    }

}