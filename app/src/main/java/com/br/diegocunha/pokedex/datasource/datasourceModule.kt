package com.br.diegocunha.pokedex.datasource

import com.apollographql.apollo.ApolloClient
import com.br.diegocunha.pokedex.BuildConfig
import com.br.diegocunha.pokedex.datasource.api.PokeDexGraphQL
import com.br.diegocunha.pokedex.datasource.repository.PokemonRepository
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val dataSourceModule = module {

    factory {

        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    single {
        ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .okHttpClient(get())
            .build()
    }

    factory { PokeDexGraphQL(get()) }

    factory { PokemonRepository(get()) }
}