package com.pangea.superhero.domain.useCase

import com.pangea.superhero.domain.model.SuperHero
import com.pangea.superhero.domain.repository.SuperHeroRepository
import javax.inject.Inject

class SearchSuperHeroesUserCase @Inject constructor(
    private val repository: SuperHeroRepository
) {
    suspend operator fun invoke(query: String): List<SuperHero> {
        return repository.searchByName(query)
    }
}