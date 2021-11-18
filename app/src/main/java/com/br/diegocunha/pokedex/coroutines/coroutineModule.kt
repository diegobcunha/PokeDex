package com.br.diegocunha.pokedex.coroutines

import org.koin.dsl.module

val coroutineModule = module {

    factory<DispatchersProvider> { ProductionDispatchersProvider }
}