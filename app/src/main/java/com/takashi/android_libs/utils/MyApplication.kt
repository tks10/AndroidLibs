package com.takashi.android_libs.utils

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig

class MyApplication : Application() {

    companion object {
        private val CONSUMER_KEY = ""
        private val CONSUMER_SECRET = ""
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
