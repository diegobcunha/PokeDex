package com.br.diegocunha.pokedex.datasource.api

import com.br.diegocunha.pokedex.datasource.model.PokemonResponse
import com.br.diegocunha.pokedex.datasource.model.SinglePokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeDexAPI {

    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Response<PokemonResponse>

    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon(
        @Path("id") id: Int
    ): Response<SinglePokemonResponse>

}