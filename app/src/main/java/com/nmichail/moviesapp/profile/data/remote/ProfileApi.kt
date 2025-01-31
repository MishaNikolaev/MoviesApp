package com.nmichail.moviesapp.profile.data.remote

import com.nmichail.moviesapp.profile.data.dto.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProfileApi {
    @GET("account/{account_id}")
    suspend fun getProfile(
        @Path("account_id") accountId: Long,
        @Query("session_id") sessionId: String
    ): ProfileDto
}