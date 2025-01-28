package com.nmichail.moviesapp.main.data.di

import android.app.Application
import androidx.room.Room
import com.nmichail.moviesapp.main.data.local.MovieDao
import com.nmichail.moviesapp.main.data.local.MovieDatabase
import com.nmichail.moviesapp.main.data.remote.MovieApiService
import com.nmichail.moviesapp.main.data.repository.MovieRepositoryImpl
import com.nmichail.moviesapp.main.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "movies.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}