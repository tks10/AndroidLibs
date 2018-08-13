package com.takashi.android_libs

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sub.*
import android.widget.Toast
import android.util.Log
import com.takashi.android_libs.usecase.ApiManager
import com.takashi.android_libs.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import retrofit2.Response


class SubActivity : AppCompatActivity() {
    val list = ArrayList<String>()
    val mAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.sub_activity)

        initViews()


    }

    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)

        val currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    fun signUp(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Auth", "createUserWithEmail:success")
                        Toast.makeText(this@SubActivity, "Oooo!", Toast.LENGTH_SHORT).show()
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Auth", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@SubActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

    fun signIn(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Auth", "signInWithEmail:success")
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Auth", "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@SubActivity, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                    // ...
                }
    }

    fun updateUI(user: FirebaseUser?){
        val msg = user?.let {
            list.add("Name: " + (it.displayName ?: "null"))
            list.add("Email: " + (it.email ?: "null"))
            val photoUri = it.photoUrl ?: "null"
            val emailVerified = it.isEmailVerified
            val uid = it.uid
            "Update Success"
        } ?: "Update Fail"

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(token: Token){
        Log.d("DUMMY", token.token)
    }



    fun initViews(){

        initList(2)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyAdapter(list)

        button2.setOnClickListener{


//            var token = getPreferences(Context.MODE_PRIVATE).let {
//                it.getString("TOKEN", "")
//            }

            if (Token.get() == null){
                Api.getService().signIn(SignIn("test@gmail.com", "testuser"))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( object: DisposableSingleObserver<Response<Token>>(){
                            override fun onSuccess(response: Response<Token>) {
                                if (response.isSuccessful){
//                                     getPreferences(Context.MODE_PRIVATE).let {
//                                         val editer = it.edit()
//                                         val t = response.body()!!.token
//                                         editer.putString("TOKEN", t)
//                                         editer.apply()
//                                         Log.d("TOKEN", "Stored Pref.")
//                                         Log.d("TOKEN", t)
//                                         t
//                                    }
                                    Token.put(response.body()!!)
                                    Log.d("SignIn", "Signed In.")
                                } else {
                                    Token.delete()
                                    Log.e("SignIn", "Failed Signing In.")
                                }
                            }

                            override fun onError(e: Throwable) {
                                Log.e("SignIn", e.toString())
                            }
                        })
            } else {
                Log.d("SignIn", "Already Signed In.")
            }


            val res = this@SubActivity.resources
            val imageOriginal = BitmapFactory.decodeResource(res, R.drawable.fig5)
            val base64 = ImageConverter.convertToBase64(imageOriginal)
            val image = ImageConverter.convertToBitmap(base64)
            image.let {
                //imageView.setImageBitmap(it)
            }
        }

        button3.setOnClickListener {
            Api.getService().sendMessage(MessageSender("string", "GUOOOO"))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<Response<Message>>(){
                        override fun onSuccess(response: Response<Message>) {
                            if (response.isSuccessful){
                                val body = response.body()!!
                                Log.d("GetUser", "Succeeded.")
                                Log.d("GetUser", body.created_at)
                            } else {
                                Log.e("GetUser", "Failed")
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.e("GetUser", e.toString())
                        }
                    })
        }
    }


    fun initList(size: Int): ArrayList<String>{
        for (i in 1..size){
            list.add(i.toString())
        }

        return list
    }
}
