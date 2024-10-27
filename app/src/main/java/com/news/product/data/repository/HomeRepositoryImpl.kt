package com.news.product.data.repository

import com.news.product.data.source.HomeService
import com.news.product.domain.model.ProductModel
import com.news.product.domain.repository.HomeRepository
import com.news.product.utils.Constants
import com.news.product.utils.Resource
import com.news.product.utils.toProductModel
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeService: HomeService) :
    HomeRepository {
    override suspend fun getProduct(): Flow<Resource<List<ProductModel>>> = flow {
        emit(Resource.Loading)
        homeService.getProduct().suspendOnSuccess {
            if (!this.data.products.isNullOrEmpty()) {
                emit(Resource.Success(data.products!!.map { it!!.toProductModel() }))
            }
        }.suspendOnError {
            try {
                when (this.statusCode) {
                    StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                    else -> emit(Resource.Error("Unable to fetch data"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(Constants.GENERAL_ERROR_MESSAGE))
            }
        }.suspendOnException { emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE)) }
    }

    override suspend fun getProductDetails(id: String): Flow<Resource<ProductModel>> = flow {
        emit(Resource.Loading)
        homeService.getProductDetails(id).suspendOnSuccess {
            val response = this.data
            if (response != null) {
                emit(Resource.Success(response.toProductModel()))
            } else emit(Resource.Error("Error found"))
        }.suspendOnError {
            try {
                when (this.statusCode) {
                    StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                    else -> emit(Resource.Error("Unable to fetch data"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(Constants.GENERAL_ERROR_MESSAGE))
            }
        }.suspendOnException { emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE)) }
    }
}