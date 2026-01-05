package com.pangea.superhero.domain.model

data class SuperHeroDetail(
    val name: String,
    val fullName: String,
    val publisher: String,
    val imageUrl: String,
    val powerStats: PowerStats
)

data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)