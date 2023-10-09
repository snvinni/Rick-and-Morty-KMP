package data.service

import core.Resource
import core.toResource
import data.response.CharactersResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class RickAndMortyService(private val client: HttpClient = HttpClient()) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun getCharacters(page : Int): Resource.Result<CharactersResponse, Throwable> {

        return runCatching {
            val response = client.get("https://rickandmortyapi.com/api/character") {
                parameter("page", page)
            }

            json.decodeFromString(
                CharactersResponse.serializer(),
                response.bodyAsText()
            )
        }.toResource()
    }
}