package com.personal.coach.app.entity

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.personal.coach.app.constants.ConstantsTinyDB
import com.personal.coach.app.db.TinyDB

/**
 * Created by renan on 18/06/16.
 */
class User(
        var username: String = String(),
        var nickname: String = String(),
        var email: String = String(),
        var sessionToken: String = String(),
        var password: String = String()
) {

    @SerializedName("objectId")
    var id = String()

    companion object {
        fun getUser(context: Context): User = User(
                username = TinyDB(context).getString(ConstantsTinyDB.USERNAME),
                nickname = TinyDB(context).getString(ConstantsTinyDB.NICKNAME),
                email = TinyDB(context).getString(ConstantsTinyDB.EMAIL),
                sessionToken = TinyDB(context).getString(ConstantsTinyDB.SESSION_TOKEN)
        )

        fun isLogged(context: Context): Boolean {
            return TinyDB(context).getString(ConstantsTinyDB.SESSION_TOKEN).isNotBlank()
        }

    }

    fun save(context: Context) {
        TinyDB(context).putString(ConstantsTinyDB.SESSION_TOKEN, sessionToken)
        TinyDB(context).putString(ConstantsTinyDB.NICKNAME, nickname)
        TinyDB(context).putString(ConstantsTinyDB.USERNAME, username)
        TinyDB(context).putString(ConstantsTinyDB.EMAIL, email)
    }
}