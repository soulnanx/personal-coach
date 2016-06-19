package com.hello.kotlin.app.http.client

import com.hello.kotlin.app.entity.Repository
import com.hello.kotlin.app.entity.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import rx.Observable

/**
 * Created by renan on 18/06/16.
 */
interface GitHubClient {
    @GET("users/{user}")
    fun getUser(@Path("user") username: String): Observable<User>

    @GET
    fun followers(@Url url: String): Observable<List<User>>

    @GET
    fun repositories(@Url url: String): Observable<List<Repository>>
}