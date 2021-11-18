package com.br.diegocunha.pokedex.datasource.api

import com.apollographql.apollo.ApolloClient
import com.br.diegocunha.pokedex.SamplePokeAPIqueryQuery
import com.br.diegocunha.pokedex.datasource.model.PokemonModel
import com.br.diegocunha.pokedex.datasource.model.PokemonResult
import com.br.diegocunha.pokedex.ui.state.GetState
import com.squareup.moshi.Types

class PokeDexGraphQL(private val apolloClient: ApolloClient) {

    suspend fun main(): GetState<List<PokemonResult>> {
        val query = SamplePokeAPIqueryQuery()
        val type = Types.newParameterizedType(GraphQLResponse::class.java, PokemonModel::class.java)
        return apolloClient.queryState<SamplePokeAPIqueryQuery.Data, PokemonModel>(query, type)
            .map {
                it?.pokemon_v2_pokemonspecies
            }
    }
}