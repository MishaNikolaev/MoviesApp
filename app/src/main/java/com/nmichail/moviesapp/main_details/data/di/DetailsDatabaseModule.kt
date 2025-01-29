package com.nmichail.moviesapp.main_details.data.di

import android.content.Context
import androidx.room.Room
import com.nmichail.moviesapp.main_details.data.local.MovieDetailsDao
import com.nmichail.moviesapp.main_details.data.local.MovieDetailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsDatabaseModule {

    @Provides
    @Singleton
    fun provideDetailsDatabase(
        @ApplicationContext context: Context
    ): MovieDetailsDatabase {
        return Room.databaseBuilder(
            context,
            MovieDetailsDatabase::class.java,
            "details_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDao(database: MovieDetailsDatabase): MovieDetailsDao {
        return database.movieDetailsDao()
    }
}