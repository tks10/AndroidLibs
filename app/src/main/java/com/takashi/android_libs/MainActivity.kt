package com.takashi.android_libs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.twitter.sdk.android.core.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val CONSUMER_KEY = "H6NT0hh2n4rFJK1TNGjU6Q"
        val CONSUMER_SECRET = "XgmyI4vkNpv17eGVJSPfT0dH6QrJfKw5FSQznxyJx"

        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig(CONSUMER_KEY, CONSUMER_SECRET))
                .debug(true)
                .build()

        Twitter.initialize(config)

        setContentView(R.layout.activity_main)

        setUpViews()
    }

    fun setUpViews(){
        button.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener{
            if (TwitterCore.getInstance().sessionManager.activeSession == null) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(this@MainActivity, "Already signed in", Toast.LENGTH_LONG)
                toast.show()
                val token = TwitterCore.getInstance().sessionManager.activeSession.authToken

            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
