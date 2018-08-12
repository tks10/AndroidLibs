package com.takashi.android_libs.usecase

import android.util.Log
import com.takashi.android_libs.utils.Api
import com.takashi.android_libs.utils.MyUser
import com.takashi.android_libs.utils.RandomUserDemo
import com.takashi.android_libs.utils.Token
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class ApiManager{
    companion object {
        fun getInformation(){
            Api.getService().apiDemo()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<RandomUserDemo>(){
                        override fun onSuccess(t: RandomUserDemo) {
                            EventBus.getDefault().post(t)
                        }

                        override fun onError(e: Throwable) {
                            Log.e("Uooooo", e.toString())
                        }
                    })
        }

        fun dummy(){
            Api.getService().dummy()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<Token>(){
                        override fun onSuccess(token: Token) {
                            EventBus.getDefault().post(token)
                        }

                        override fun onError(e: Throwable) {
                            Log.e("Uooooo", e.toString())
                        }
                    })
        }

        fun getUser(email: String, password: String){
            Api.getService().getUser(email, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( object: DisposableSingleObserver<MyUser>(){
                        override fun onSuccess(user: MyUser) {
                            EventBus.getDefault().post(user)
                        }

                        override fun onError(e: Throwable) {
                            Log.e("Uooooo", e.toString())
                        }
                    })
        }
    }
}