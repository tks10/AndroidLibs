package com.takashi.android_libs.utils

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api")
    fun apiDemo(): Single<RandomUserDemo>

    @POST("api/auth/dummy/")
    fun dummy(): Single<Token>

    @POST("api/auth/user/")
    fun getUser(@Body user: User): Single<Response<Token>>

    @POST("api/auth/refresh/")
    fun refreshToken(@Body token: Token): Single<Response<Token>>

    @POST("api/auth/verify/")
    fun verifyToken(@Body token: Token): Single<Response<Token>>
}
