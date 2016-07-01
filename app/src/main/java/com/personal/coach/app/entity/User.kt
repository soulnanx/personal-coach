package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by renan on 18/06/16.
 */
class User(
        open val username: String = "",
        open val password: String = "",
        open val nickname: String = "",
        open val email: String = "") {

    @SerializedName("objectId")
    open val id:String? = null

    open val sessionToken = String()
}