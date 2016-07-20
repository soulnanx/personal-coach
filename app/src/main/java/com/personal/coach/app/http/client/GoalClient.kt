package com.personal.coach.app.http.client

import com.personal.coach.app.dto.ParseResult
import com.personal.coach.app.dto.ParseResultGoal
import com.personal.coach.app.entity.Goal
import com.personal.coach.app.entity.Person
import retrofit2.http.*
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface GoalClient {
    @POST("classes/Goal")
    fun save(@Body goal: Goal): Observable<Goal>

    @GET("classes/Goal/{id}")
    fun findById(@Path("id") id: String): Observable<Goal>

    @GET("classes/Goal?order=-createdAt")
    fun findAll(): Observable<ParseResultGoal>
}
