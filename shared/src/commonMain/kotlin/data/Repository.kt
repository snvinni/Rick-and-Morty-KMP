package data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Repository(
    private val client: HttpClient = HttpClient()
) {

    suspend fun get(): String {

        val httpResponse = client.request("https://rickandmortyapi.com/api") {
            method = io.ktor.http.HttpMethod.Get
        }

        return httpResponse.bodyAsText()
    }
}