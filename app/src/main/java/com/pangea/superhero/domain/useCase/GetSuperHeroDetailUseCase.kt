package com.pangea.superhero.domain.useCase

import com.pangea.superhero.domain.repository.SuperHeroRepository
import javax.inject.Inject

class GetSuperHeroDetailUseCase @Inject constructor(
    private val repository: SuperHeroRepository
) {
    suspend operator fun invoke(id: String) = repository.getHeroDetail(id)

}