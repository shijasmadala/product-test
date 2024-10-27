package com.news.product.presentation.details

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
class ProductDetailViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {
    private val _detailState = MutableStateFlow<DetailUiState>(DetailUiState.Empty)
    val detailState = _detailState.asStateFlow()

    fun getProductDetail(id: String) = viewModelScope.launch {
        homeRepository.getProductDetails(id).collectLatest { data ->
            when (data) {
                Resource.Empty -> {
                    _detailState.value = DetailUiState.Empty
                }

                is Resource.Error -> {
                    _detailState.value = DetailUiState.Error(data.error)
                }

                Resource.Loading -> {
                    _detailState.value = DetailUiState.Loading
                }

                is Resource.Success -> {
                    _detailState.value = DetailUiState.Success(data.value)
                }
            }
        }
    }
}