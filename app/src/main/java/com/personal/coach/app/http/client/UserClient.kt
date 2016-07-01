package com.personal.coach.app.http.client

import com.personal.coach.app.entity.User
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface UserClient {

    @GET("login")
    fun login(
            @Query("username") username:String,
            @Query("password") password:String): Observable<User>

    @POST("users")
    fun signUp(@Body user:User): Observable<User>

    @GET("users/{id}")
    fun findById(@Path("id") id:String): Observable<User>
}


