package com.takashi.android_libs.utils

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiService {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String) : Observable<User> // Callではなく、Observable

    @GET("api")
    fun apiDemo(): Observable<RandomUserDemo>
}
