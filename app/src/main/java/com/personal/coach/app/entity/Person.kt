package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by renan on 19/06/16.
 */
class Person (open val name:String? = null){

    @SerializedName("objectId")
    open val id:String? = null
}