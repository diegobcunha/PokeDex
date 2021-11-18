package com.br.diegocunha.pokedex

import android.app.Application
import com.br.diegocunha.pokedex.coroutines.coroutineModule
import com.br.diegocunha.pokedex.datasource.dataSourceModule
import com.br.diegocunha.pokedex.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokeDexApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PokeDexApplication)
            modules(
                listOf(
                    coroutineModule,
                    dataSourceModule,
                    uiModule
                )
            )
        }
    }
}