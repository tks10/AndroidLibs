package com.takashi.android_libs.utils

import com.takashi.android_libs.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class Api {
    companion object {
        private val apiService: ApiService by lazy { createService() }

        fun getService(): ApiService = apiService

        fun createService(): ApiService {
            val apiUrl = "http://10.0.2.2:8000/"
            val client = builderHttpClient() // OkHttpClient に logging の設定を追加
            val retrofit = Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .addConverterFactory(MoshiConverterFactory.create()) // Retrofit に gson を設定
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        private fun builderHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }

            return client.build()
        }
    }
}
