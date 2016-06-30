package com.personal.coach.app.http.client

import com.personal.coach.app.dto.ParseResult
import com.personal.coach.app.entity.Day
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface DayClient {
    @POST("classes/Day")
    fun save(@Body person: Day): Observable<Day>

    @GET("classes/Day/{id}")
    fun findById(@Path("id") id: String): Observable<Day>

    @GET("classes/Day")
    fun findAll(): Observable<ParseResult>
}


