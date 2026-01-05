package com.pangea.superhero.ui.state

import com.pangea.superhero.domain.model.SuperHeroDetail

sealed class SuperHeroDetailUiState {
    object Loading : SuperHeroDetailUiState()
    data class Success(val hero: SuperHeroDetail) : SuperHeroDetailUiState()
    data class Error(val message: String) : SuperHeroDetailUiState()

}