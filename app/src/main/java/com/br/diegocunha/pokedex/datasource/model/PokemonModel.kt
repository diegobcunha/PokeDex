package com.br.diegocunha.pokedex.datasource.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonResult(
    val name: String,
    val url: String
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SinglePokemonResponse(
    val sprites: Sprites,
    val stats: List<Stats>,
    val height: Int,
    val weight: Int
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Stat(
    val name: String,
    val url: String
) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class Sprites(
    @Json(name = "back_default") val backDefault: String,
    @Json(name = "back_shiny") val backShiny: String,
    @Json(name = "front_default") val frontDefault: String,
    @Json(name = "front_shiny") val frontShiny: String
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "base_stat") val baseStat: Int,
    val effort: Int,
    val stat: Stat
) : Parcelable