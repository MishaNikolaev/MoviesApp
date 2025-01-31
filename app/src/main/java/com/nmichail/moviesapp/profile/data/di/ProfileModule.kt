package com.nmichail.moviesapp.profile.data.di

import com.nmichail.moviesapp.profile.data.remote.ProfileApi
import com.nmichail.moviesapp.profile.data.repository.ProfileRepository
import com.nmichail.moviesapp.profile.domain.repository.ProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
    }

    @Provides
    fun provideProfileRepository(api: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(api)
    }
}