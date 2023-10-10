package data.repository

import core.util.Resource
import core.util.mapError
import core.util.mapSuccess
import data.response.CharactersResponse
import data.service.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RickAndMortyRepository(
    private val service: RickAndMortyService = RickAndMortyService()
) {

    fun getCharacters(page: Int): Flow<Resource.Result<CharactersResponse, String>> {
        return flow {

            val results = service.getCharacters(page = page)
                .mapSuccess { it }
                .mapError { it.message ?: "Error" }

            emit(results)
        }
    }
}
