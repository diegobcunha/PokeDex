package com.br.diegocunha.pokedex.datasource

import com.br.diegocunha.pokedex.datasource.api.PokeDexAPI
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import com.br.diegocunha.pokedex.BuildConfig
import retrofit2.converter.moshi.MoshiConverterFactory

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

    factory{ MoshiConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<MoshiConverterFactory>())
            .build()
    }

    factory {
        val retrofit: Retrofit = get()
        retrofit.create(PokeDexAPI::class.java)
    }
}