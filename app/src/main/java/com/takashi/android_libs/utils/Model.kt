package com.takashi.android_libs.utils

data class User(
        val login: String,
        val id: Int,
        val avatar_url: String,
        val gravatar_id: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val starred_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val events_url: String,
        val received_events_url: String,
        val type: String,
        val site_admin: Boolean,
        val name: String,
        val company: String?,
        val blog: String?,
        val location: String?,
        val email: String?,
        val hireable: Boolean?,
        val bio: String?,
        val public_repos: Int,
        val public_gists: Int,
        val followers: Int,
        val following: Int,
        val created_at: String,
        val updated_at: String
)

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
