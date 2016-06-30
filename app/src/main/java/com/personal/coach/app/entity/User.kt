package com.personal.coach.app.entity

import com.google.gson.annotations.SerializedName
import java.net.URL

/**
 * Created by renan on 18/06/16.
 */
class User{

    open var login:String? = null
    open var name:String? = null

    @SerializedName("followers_url")
    open var followersUrl:URL? = null

    @SerializedName("repos_url")
    open var reposUrl:URL? = null
}