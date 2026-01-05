package com.pangea.superhero.ui.state

import com.pangea.superhero.domain.model.SuperHero

sealed class SuperHeroUiState {
    object Idle : SuperHeroUiState()
    object Loading : SuperHeroUiState()
    data class Success(val heroes: List<SuperHero>) : SuperHeroUiState()
    data class Error(
        val message: String? = null
    ) : SuperHeroUiState()

}