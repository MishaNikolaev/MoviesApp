package com.nmichail.moviesapp.auth.data.remote

import com.nmichail.moviesapp.auth.data.dto.GuestSessionResponse
import com.nmichail.moviesapp.auth.data.dto.LoginRequestTokenBody
import com.nmichail.moviesapp.auth.data.dto.RequestTokenResponse
import com.nmichail.moviesapp.auth.data.dto.SessionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @GET("authentication/guest_session/new")
    suspend fun createGuestSession(
        @Query("api_key") apiKey: String
    ): GuestSessionResponse

    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String
    ): RequestTokenResponse

    @POST("authentication/token/validate_with_login")
    suspend fun validateWithLogin(
        @Query("api_key") apiKey: String,
        @Body loginBody: LoginRequestTokenBody
    ): RequestTokenResponse

    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") apiKey: String,
        @Body requestToken: Map<String, String>
    ): SessionResponse
}