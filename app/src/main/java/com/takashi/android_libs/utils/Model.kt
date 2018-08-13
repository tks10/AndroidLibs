package com.takashi.android_libs.utils


data class Token(val token: String){
    companion object {
        private var cache: Token? = null

        fun get(): Token?{
            return cache
        }

        fun put(token: Token){
            cache = token
        }

        fun delete(){
            cache = null
        }
    }
}

data class SignIn(val email: String, val password: String)

data class User(val id: String, val name: String)

data class Room(
        val id: String,
        val user_count: Int)

data class Uuid(val uuid: String)

data class Id(val id: String)

data class MessageSender(
        val room_id: String,
        val content: String
)

data class Message(
        val user: User,
        val room: Room,
        val content: String,
        val created_at: String
)

data class MessageContainer(
        val count: Int,
        // val next: Any?,
        // val previous: Any?,
        val results: List<Message>
)

data class UserContainer(
        val count: Int,
        // val next: Any?,
        // val previous: Any?,
        val results: List<User>
)
