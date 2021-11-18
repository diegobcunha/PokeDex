package com.br.diegocunha.pokedex.datasource.extensions

import com.apollographql.apollo.api.Response


fun <T> Response<T>.getOrThrow(): T? {
    val errorsList = errors
    return if (!errorsList.isNullOrEmpty()) {
        throw Exception(errorsList.first().message)
    } else {
        data
    }
}