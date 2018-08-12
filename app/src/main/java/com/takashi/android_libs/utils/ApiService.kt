package com.takashi.android_libs.utils

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String) : Observable<User> // Callではなく、Observable

    @GET("api")
    fun apiDemo(): Single<RandomUserDemo>
}
