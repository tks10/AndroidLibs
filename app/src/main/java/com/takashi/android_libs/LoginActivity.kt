package com.takashi.android_libs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.takashi.android_libs.utils.MyApplication
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterLoginButton
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        setContentView(R.layout.activity_login)

        loginButton?.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>) {
                // Do something with result, which provides a TwitterSession for making API calls

                val toast = Toast.makeText(this@LoginActivity, "ログイン成功", Toast.LENGTH_LONG)
                toast.show()

                val a = result.response.body() as TwitterSession


            }

            override fun failure(exception: TwitterException) {
                // Do something on failure

                val toast = Toast.makeText(this@LoginActivity, "ログイン失敗", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        loginButton?.onActivityResult(requestCode, resultCode, data)
    }
}
