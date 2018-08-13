package com.takashi.android_libs.utils


data class RandomUserDemo(val info: Info,
                          val results: List<Result>)

data class Info(val seed: String?,
                val results: Int?,
                val page: Int?,
                val version: String?)

data class Result(val gender: String?,
                  val email: String?,
                  val phone: String?,
                  val cell: String?)

data class Token(val token: String)

data class User(val email: String, val password: String)

