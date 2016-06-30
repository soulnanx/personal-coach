package com.personal.coach.app.http.client

import com.personal.coach.app.dto.ParseResult
import com.personal.coach.app.entity.Person
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface PersonClient {
    @POST("classes/Person")
    fun save(@Body person: Person): Observable<Person>

    @GET("classes/Person/{id}")
    fun findById(@Path("id") id: String): Observable<Person>

    @GET("classes/Person?order=-createdAt")
    fun findAll(): Observable<ParseResult>
}
