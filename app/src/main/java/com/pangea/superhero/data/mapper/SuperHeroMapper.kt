package com.pangea.superhero.data.mapper

import com.pangea.superhero.data.dto.SuperheroItemResponse
import com.pangea.superhero.domain.model.SuperHero


fun SuperheroItemResponse.toDomain(): SuperHero {
    return SuperHero(
        id = superheroId,
        name = name,
        imageUrl = superheroImage.url
    )
}