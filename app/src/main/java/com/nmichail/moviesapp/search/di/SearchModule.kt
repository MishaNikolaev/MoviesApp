package com.nmichail.moviesapp.search.di

import com.nmichail.moviesapp.search.data.remote.SearchApi
import com.nmichail.moviesapp.search.data.repository.SearchRepositoryImpl
import com.nmichail.moviesapp.search.domain.repository.SearchRepository
import com.nmichail.moviesapp.search.domain.usecases.SearchMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchMoviesUseCase(repository: SearchRepository): SearchMoviesUseCase {
        return SearchMoviesUseCase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}