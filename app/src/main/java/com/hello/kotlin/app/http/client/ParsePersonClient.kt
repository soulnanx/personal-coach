package com.hello.kotlin.app.http.client

import com.hello.kotlin.app.entity.Person
import org.json.JSONObject
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface ParsePersonClient {
    @POST("classes/Person")
    fun save(@Body person: Person): Observable<Person>

    @GET("classes/Person/{id}")
    fun findById(@Path("id") id: String): Observable<Person>
}
