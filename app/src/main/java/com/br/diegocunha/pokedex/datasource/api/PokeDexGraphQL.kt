package com.br.diegocunha.pokedex.datasource.api

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.br.diegocunha.pokedex.PokemonDetailQuery
import com.br.diegocunha.pokedex.SamplePokeAPIqueryQuery
import com.br.diegocunha.pokedex.datasource.model.Pokemon
import com.br.diegocunha.pokedex.datasource.model.PokemonModel
import com.br.diegocunha.pokedex.datasource.model.PokemonResult
import com.br.diegocunha.pokedex.ui.state.GetState
import com.squareup.moshi.Types

class PokeDexGraphQL(private val apolloClient: ApolloClient) {

    suspend fun listOfPokemons(): GetState<List<PokemonResult>> {
        val query = SamplePokeAPIqueryQuery()
        val type = Types.newParameterizedType(GraphQLResponse::class.java, PokemonModel::class.java)
        return apolloClient.queryState<SamplePokeAPIqueryQuery.Data, PokemonModel>(query, type)
            .map {
                it?.pokemons
            }
    }

    suspend fun getPokemonDetail(): GetState<List<Pokemon>> {
        val type = Types.newParameterizedType(
            GraphQLResponse::class.java, PokemonModel::class.java
        )
        val listOfPokemons = listOfPokemons().map { currentPokemon ->
            currentPokemon?.map { current ->
                val query = PokemonDetailQuery(current.id)
                try {
                    apolloClient.queryResponseState<PokemonDetailQuery.Data, PokemonModel>(
                        query,
                        type
                    )
                        .getOrThrow().pokemon
                } catch (e: Exception) {
                    Log.e("HomeException", e.message.orEmpty())
                    null
                }
            }?.filterNotNull()
        }

        return listOfPokemons


    }
}