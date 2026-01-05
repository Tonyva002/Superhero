package com.pangea.superhero.data.repository

import com.pangea.superhero.data.mapper.toDomain
import com.pangea.superhero.data.remote.ApiService
import com.pangea.superhero.domain.model.SuperHero
import com.pangea.superhero.domain.model.SuperHeroDetail
import com.pangea.superhero.domain.repository.SuperHeroRepository
import javax.inject.Inject
import kotlin.collections.map

class SuperHeroRepositoryImpl @Inject constructor(
    private val api: ApiService
): SuperHeroRepository {
    override suspend fun searchByName(query: String): List<SuperHero> {
        val response = api.getSuperheroes(query)

        if (!response.isSuccessful) throw Exception("Network error")

        return response.body()?.superheroes
            ?.map { it.toDomain() }
            ?: emptyList()
    }

    override suspend fun getHeroDetail(id: String): SuperHeroDetail {
        val response = api.getSuperheroDetail(id)

        if (!response.isSuccessful || response.body() == null) {
            throw Exception("Error loading hero detail")
        }
        return response.body()!!.toDomain()
    }

}