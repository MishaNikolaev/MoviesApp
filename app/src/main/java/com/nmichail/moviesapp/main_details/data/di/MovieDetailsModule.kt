package com.nmichail.moviesapp.main_details.data.di

import com.nmichail.moviesapp.main_details.data.local.MovieDetailsDao
import com.nmichail.moviesapp.main_details.data.remote.MovieDetailsApi
import com.nmichail.moviesapp.main_details.data.repository.MovieDetailsRepositoryImpl
import com.nmichail.moviesapp.main_details.domain.repository.MovieDetailsRepository
import com.nmichail.moviesapp.main_details.domain.usecases.GetMovieDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        api: MovieDetailsApi,
        dao: MovieDetailsDao
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(
        repository: MovieDetailsRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}