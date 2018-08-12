package com.takashi.android_libs.utils

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String) : Observable<User> // Callではなく、Observable

    @GET("api")
    fun apiDemo(): Single<RandomUserDemo>

    @POST("api/auth/dummy")
    fun dummy(): Single<Token>

    @FormUrlEncoded
    @POST("api/auth/user")
    fun getUser(@Field("email") email: String, @Field("password") password: String): Single<MyUser>
}
