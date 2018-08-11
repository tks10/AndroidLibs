package com.takashi.android_libs

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.takashi.android_libs.utils.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sub.*
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.R.attr.password




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

        val currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
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

    fun initViews(){

        initList(2)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MyAdapter(list)

        button2.setOnClickListener{
            signUp("tktktks@gmail.com", "tyujmko1013")
            //list.add("OooooO")
            //recycler_view.adapter?.notifyDataSetChanged()
        }


    }

    fun initList(size: Int): ArrayList<String>{
        for (i in 1..size){
            list.add(i.toString())
        }

        return list
    }
}
