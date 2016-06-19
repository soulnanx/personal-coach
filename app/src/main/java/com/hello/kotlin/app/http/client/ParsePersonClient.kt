package com.hello.kotlin.app.http.client

import com.hello.kotlin.app.entity.Person
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface ParsePersonClient {
    @POST("classes/Person")
    fun save(@Body person: Person): Observable<Person>
}
