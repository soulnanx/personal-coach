package com.hello.kotlin.app.http.client

import com.hello.kotlin.app.PeopleDTO
import com.hello.kotlin.app.entity.Person
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface ParsePersonClient {
    @POST("classes/Person")
    fun save(@Body person: Person): Observable<Person>

    @GET("classes/Person/{id}")
    fun findById(@Path("id") id: String): Observable<Person>

    @GET("classes/Person")
    fun findAll(): Observable<PeopleDTO>
}
