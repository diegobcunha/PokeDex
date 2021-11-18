package com.br.diegocunha.pokedex.datasource.api

import androidx.annotation.Keep
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.toJson
import com.br.diegocunha.pokedex.coroutines.doSuspendReturn
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

/**
 * A representation of a Json graphql response
 */
@Keep
@JsonClass(generateAdapter = true)
data class GraphQLResponse<T>(val data: T?, val errors: List<GraphQLError>?)

/**
 * Map the Apollo Response to a custom WarrenResponse. This method use Json serialization and
 * deserialization, so avoid multiple calls.
 *
 * Used to decrease the number of Apollo imports, number of apollo mappers and number of unit
 * tests. Don't use Apollo data classes in project
 */
suspend inline fun <D : Operation.Data, T> Response<D>.asResponse(type: Type): GraphQLResponse<T> {
    var error: List<GraphQLError>? = null
    if (hasErrors()) {
        error = errors?.asGraphQLErrors()
    }

    val json = data?.toJson() ?: "{}"
    val moshi = Moshi.Builder().build()

    val response = moshi.adapter<GraphQLResponse<T>>(type).doSuspendReturn {
        fromJson(json)
    }
    return GraphQLResponse(response?.data, error)
}

/**
 * Returns the graphql data from the response or throws if has errors
 */
fun <T> GraphQLResponse<T>.getOrThrow(): T? {
    return if (errors != null) {
        throw createResponseException()
    } else {
        data
    }
}

/**
 * Maps the GraphQLResponse to an exception
 */
fun <T> GraphQLResponse<T>.createResponseException(): Exception {
    return GraphQLResponseException(errors)
}

/**
 * A representation of Json graphql error
 */
@Keep
@JsonClass(generateAdapter = true)
data class GraphQLError(
    val message: String? = null,
    val customAttributes: Map<String, Any?>? = null
)

fun List<Error>.asGraphQLErrors(): List<GraphQLError> = map { it.asGraphQLError() }

fun Error.asGraphQLError(): GraphQLError {
    return GraphQLError(
        message = message,
        customAttributes = customAttributes
    )
}