package com.nmichail.moviesapp.auth.di

import android.content.Context
import androidx.room.Room
import com.nmichail.moviesapp.auth.data.local.AuthDatabase
import com.nmichail.moviesapp.auth.data.repository.LocalRepositoryImpl
import com.nmichail.moviesapp.auth.domain.repository.AuthLocalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AuthDatabase {
        return Room.databaseBuilder(
            context,
            AuthDatabase::class.java,
            "auth_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSessionDao(authDatabase: AuthDatabase) = authDatabase.sessionDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): AuthLocalRepository
}