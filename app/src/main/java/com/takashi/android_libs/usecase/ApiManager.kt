package com.takashi.android_libs.usecase

import android.util.Log
import com.takashi.android_libs.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import retrofit2.Response

class ApiManager{
    companion object {


        fun getUser(email: String, password: String){
            Api.getService().signIn(SignIn(email, password))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<Response<Token>>(){
                        override fun onSuccess(user: Response<Token>) {
                            EventBus.getDefault().post(user)
                        }

                        override fun onError(e: Throwable) {
                            Log.e("Uooooo", e.toString())
                        }
                    })
        }

        fun refreshToken(token: String){
            Api.getService().refreshToken(Token(token))
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<Response<Token>>(){
                        override fun onSuccess(user: Response<Token>) {
                            EventBus.getDefault().post(user)
                        }

                        override fun onError(e: Throwable) {
                            Log.e("Uooooo", e.toString())
                        }
                    })
        }


    }
}