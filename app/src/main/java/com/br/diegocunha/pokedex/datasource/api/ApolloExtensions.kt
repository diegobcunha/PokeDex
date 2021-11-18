package com.br.diegocunha.pokedex.datasource.api

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.br.diegocunha.pokedex.ui.state.GetState
import java.lang.reflect.Type

suspend inline fun <reified D : Operation.Data, reified T> ApolloClient.queryState(
    query: Query<D, D, Operation.Variables>,
    type: Type
): GetState<T> {
    return try {
        queryResponseState<D, T>(query, type).asState()
    } catch (e: Exception) {
        GetState.failure<T>(e)
    }
}

/**
 * Execute a query returning a GraphQLResponse. Call this method just if you care about the
 * errors and can keep the work if has errors and data together
 *
 * @param T the type of response
 */
suspend inline fun <reified D : Operation.Data, reified T> ApolloClient.queryResponseState(
    query: Query<*, D, *>,
    type: Type
): GraphQLResponse<T> {
    return query(query).asResponse<D, T>(type)
}

/**
 * Simple mapper of an apollo query call. This method can throw an exception if there is some
 * request error. Avoid use ApolloQueryCall classes directly (use the above methods).
 */
suspend inline fun <reified D : Operation.Data, reified T> ApolloQueryCall<D>.asResponse(
    type: Type
): GraphQLResponse<T> {
    val apolloResponse = asApolloResponse()
    return apolloResponse.asResponse(type)
}


/**
 * Converts an GraphQLResponse to a resource
 */
fun <T> GraphQLResponse<T>.asState(): GetState<T> {
    return if (errors.orEmpty().isEmpty() && data != null) {
        GetState.success(data)
    } else {
        GetState.failure(createResponseException())
    }
}

suspend inline fun <reified D : Operation.Data> ApolloQueryCall<D>.asApolloResponse(): Response<D> {
    return await()
}
