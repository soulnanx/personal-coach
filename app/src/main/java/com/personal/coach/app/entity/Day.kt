package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by renan on 19/06/16.
 */
class Day (
        open val score: Integer? = null,
        open val createdAt: Date? = null,
        open val message: String = ""){

    @SerializedName("objectId")
    open val id:String? = null
}