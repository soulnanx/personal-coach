package com.personal.coach.app.http.client

import com.personal.coach.app.dto.ParseResultGoal
import com.personal.coach.app.entity.Goal
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

/**
 * Created by renan on 19/06/16.
 */
interface GoalClient {
    @POST("classes/Goal")
    fun save(@Body goal: Goal): Observable<Goal>

    @GET("classes/Goal/{id}")
    fun findById(@Path("id") id: String): Observable<Goal>

    @GET("classes/Goal")
    fun findAll(): Observable<ParseResultGoal>
}
