package com.personal.coach.app.http.client

import com.personal.coach.app.dto.ParseResult
import com.personal.coach.app.entity.Day
import com.personal.coach.app.entity.User
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface UserClient {

    @FormUrlEncoded
    @GET("classes/_User")
    fun login(
            @Field("password") password:String,
            @Field("username") username:String): Observable<User>

    @POST("users")
    fun signUp(@Body user:User): Observable<User>
}


