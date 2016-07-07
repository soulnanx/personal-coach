package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by renan on 05/07/16.
 */
class Goal(open val message:String,
           open val annoyingMessage:String,
           open val begin:Date,
           open val deadLine:Date,
           open val title:String,
           open val createdAt:Date? = null){

    @SerializedName("objectId")
    open val id:String = String()

}