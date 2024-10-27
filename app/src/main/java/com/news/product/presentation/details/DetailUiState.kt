package com.news.product.presentation.details

import com.news.product.domain.model.ProductModel
import com.news.product.presentation.home.HomeUiState

sealed class DetailUiState {
    data class Success(val productDetail: ProductModel) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
    object Loading : DetailUiState()
    object Empty : DetailUiState()
}