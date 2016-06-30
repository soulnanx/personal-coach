package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by renan on 18/06/16.
 */
class Repository{

    open var owner:User? = null

    @SerializedName("full_name")
    open var fullName:String? = null
}