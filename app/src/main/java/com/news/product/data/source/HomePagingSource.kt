package com.news.product.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.product.data.dto.Product

class HomePagingSource (private val homeService: HomeService)  {
//    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
//       homeService.getProduct()
//    }
}