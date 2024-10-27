package com.news.product.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.product.domain.repository.HomeRepository
import com.news.product.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val _homeState = MutableStateFlow<HomeUiState>(HomeUiState.Empty)
    val homeState = _homeState.asStateFlow()

    init {
        getProductData()
    }

    private fun getProductData() = viewModelScope.launch {
        homeRepository.getProduct().collectLatest { data ->
            when(data){
                Resource.Empty -> {
                    _homeState.value = HomeUiState.Empty
                }
                is Resource.Error -> {
                    _homeState.value = HomeUiState.Error(data.error)
                }
                Resource.Loading -> {
                    _homeState.value = HomeUiState.Loading
                }
                is Resource.Success -> {
                    _homeState.value = HomeUiState.Success(data.value)
                }
            }
        }
    }
}