package com.takashi.android_libs.utils

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/user/")
    fun getUser(): Single<Response<User>>

    // Auth
    @POST("api/auth/refresh/")
    fun refreshToken(@Body token: Token): Single<Response<Token>>

    @POST("api/auth/user/")
    fun signIn(@Body login: SignIn): Single<Response<Token>>

    @POST("api/auth/verify/")
    fun verifyToken(@Body token: Token): Single<Response<Token>>


    // Messages
    @POST("api/messages/")
    fun sendMessage(@Body message: MessageSender): Single<Response<Message>>


    // Register
    @POST("api/register/uuid/")
    fun regist(@Body uuid: Uuid): Single<Response<Token>>


    // Rooms
    @POST("api/rooms/")
    fun createRoom(@Body id: Id): Single<Response<Room>>

    @GET("api/rooms/{id}/")
    fun getRoom(@Path("id") id: String): Single<Response<Room>>

    @POST("api/rooms/{id}/join/")
    fun joinRoom(@Path("id") id: String): Single<Response<Room>>

    @GET("api/rooms/{id}/messages/")
    fun getMessages(@Path("id") id: String): Single<Response<MessageContainer>>

    @GET("api/rooms/{id}/users/")
    fun getUsers(@Path("id") id: String): Single<Response<UserContainer>>


    // User
    @PUT("api/user")
    fun updateUserName(@Body name: String): Single<Response<User>>


    // Users
    @GET("api/users/{id}/")
    fun getUser(@Path("id") id: String): Single<Response<User>>

}
