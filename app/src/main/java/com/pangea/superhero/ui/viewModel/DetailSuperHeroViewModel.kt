package com.pangea.superhero.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangea.superhero.domain.useCase.GetSuperHeroDetailUseCase
import com.pangea.superhero.ui.state.SuperHeroDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSuperHeroViewModel @Inject constructor(
    private val getHeroDetail: GetSuperHeroDetailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<SuperHeroDetailUiState>(
        SuperHeroDetailUiState.Loading
    )
    val uiState: StateFlow<SuperHeroDetailUiState> = _uiState

    fun loadHero(id: String) {
        viewModelScope.launch {
            try {
                val hero = getHeroDetail(id)
                _uiState.value = SuperHeroDetailUiState.Success(hero)
            }catch (e: Exception) {
                _uiState.value = SuperHeroDetailUiState.Error(
                    e.message ?: "Unexpected error"
                )
            }
        }
    }
}