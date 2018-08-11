package com.takashi.android_libs.utils

import okhttp3.Interceptor
import okhttp3.Response

class BearerAuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        chain ?: return null

        val accessToken = "token" // アプリ内で保持している token をセットする
        val request = chain.request().newBuilder()
                .header("Authorization", "Bearer ${accessToken}")
                .build()
        return chain.proceed(request)
    }
}