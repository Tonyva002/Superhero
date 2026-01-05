package com.pangea.superhero.data.mapper

import com.pangea.superhero.domain.model.SuperHeroDetail
import com.pangea.superhero.data.dto.SuperHeroDetailResponse
import com.pangea.superhero.domain.model.PowerStats


fun SuperHeroDetailResponse.toDomain(): SuperHeroDetail {
    return SuperHeroDetail(
        name = name,
        fullName = biography.fullName,
        publisher = biography.publisher,
        imageUrl = image.url,
        powerStats = PowerStats(
            intelligence = powerstats.intelligence,
            strength = powerstats.strength,
            speed = powerstats.strength,
            durability = powerstats.durability,
            power = powerstats.power,
            combat = powerstats.combat
        ),
    )


}