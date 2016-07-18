package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by renan on 18/06/16.
 */
class User(
        val username: String = "",
        val password: String = "",
        val nickname: String = "",
        val email: String = "") {

    @SerializedName("objectId")
    val id = String()

    val sessionToken = String()
}