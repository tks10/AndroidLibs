package com.takashi.android_libs.utils

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig

class MyApplication : Application() {

    companion object {
        private val CONSUMER_KEY = "H6NT0hh2n4rFJK1TNGjU6Q"
        private val CONSUMER_SECRET = "XgmyI4vkNpv17eGVJSPfT0dH6QrJfKw5FSQznxyJx"
    }

    override fun onCreate() {
        super.onCreate()

//        val config = TwitterConfig.Builder(this)
//                .logger(DefaultLogger(Log.DEBUG))
//                .twitterAuthConfig(TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
//                .debug(true)
//                .build()
//
//        Twitter.initialize(config)
    }
}
