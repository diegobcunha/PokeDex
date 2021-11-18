package com.br.diegocunha.pokedex.datasource.api

class GraphQLResponseException(val errors: List<GraphQLError>?) : Exception()
