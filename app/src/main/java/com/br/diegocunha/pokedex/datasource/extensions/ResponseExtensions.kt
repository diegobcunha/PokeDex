package com.br.diegocunha.pokedex.datasource.extensions

import retrofit2.Response

fun <T> Response<T>.getOrThrow(): T? {
    return if (this.errorBody() != null) {
        throw throw Exception(errorBody()?.string())
    } else {
        this.body()
    }
}