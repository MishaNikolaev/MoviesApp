package com.nmichail.moviesapp.main.data.di

import com.nmichail.moviesapp.auth.data.remote.AuthApi
import com.nmichail.moviesapp.main.data.local.MovieDao
import com.nmichail.moviesapp.main.data.remote.MainInterceptor
import com.nmichail.moviesapp.main.data.remote.MovieApiService
import com.nmichail.moviesapp.main.data.repository.MovieRepositoryImpl
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import com.nmichail.moviesapp.main.domain.usecases.GetCachedMoviesUseCase
import com.nmichail.moviesapp.main.domain.usecases.GetMoviesUseCase
import com.nmichail.moviesapp.main_details.data.remote.MovieDetailsApi
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
object MainModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "YOUR_API_KEY"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MainInterceptor(API_KEY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: MovieApiService, movieDao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(apiService, movieDao)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCachedMoviesUseCase(repository: MovieRepository): GetCachedMoviesUseCase {
        return GetCachedMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMovieApiServiceDetails(retrofit: Retrofit): MovieDetailsApi {
        return retrofit.create(MovieDetailsApi::class.java)
    }

}