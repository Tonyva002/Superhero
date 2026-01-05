package com.pangea.superhero.domain.repository

import com.pangea.superhero.domain.model.SuperHero
import com.pangea.superhero.domain.model.SuperHeroDetail

interface SuperHeroRepository {
    suspend fun searchByName(query: String): List<SuperHero>
    suspend fun getHeroDetail(id: String): SuperHeroDetail
}