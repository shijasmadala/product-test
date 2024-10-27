package com.news.product.di

import com.news.product.data.repository.HomeRepositoryImpl
import com.news.product.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}