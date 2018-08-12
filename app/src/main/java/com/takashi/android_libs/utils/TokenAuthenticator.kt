package com.takashi.android_libs.utils

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    private var count = 1

    override fun authenticate(route: Route, response: Response): Request? {
        // 3 回リトライを行う。 return null で authenticate メソッドの loop から抜ける
        if (this.retryCount(response = response) > 3) {
            return null
        }

        // アプリ内で保持している refreshToken
        val refreshToken = "refreshToken"
        // refreshToken を利用して token を更新する
        val newToken = this.updateToken(refreshToken = refreshToken) ?: return null

        return response.request().newBuilder().header("www-Authorization", "Bearer ${newToken}")?.build()
    }

    private fun updateToken(refreshToken: String): String? {
        // Retrofit の execute （同期）メソッドで token 更新のリクエストを行う
        return "newToken"
    }

    private fun retryCount(response: Response): Int {
        response.priorResponse()?.let {
            count += 1
        }
        return count
    }
}
