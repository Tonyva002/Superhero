package com.pangea.superhero.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangea.superhero.domain.useCase.SearchSuperHeroesUserCase
import com.pangea.superhero.ui.state.SuperHeroUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val searchHeroes: SearchSuperHeroesUserCase
) : ViewModel()  {

    private val _uiState = MutableStateFlow<SuperHeroUiState>(SuperHeroUiState.Idle)

    val uiState: StateFlow<SuperHeroUiState> = _uiState

    fun search(query: String) {
        viewModelScope.launch {
            _uiState.value = SuperHeroUiState.Loading

            try {
                val heroes = searchHeroes(query)
                _uiState.value = SuperHeroUiState.Success(heroes)
            }catch (e: Exception) {
                _uiState.value = SuperHeroUiState.Error(e.message ?: "Unexpected error")
            }
        }
    }
}