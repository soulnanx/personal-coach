package com.personal.coach.app.dto

import com.google.gson.Gson
import retrofit2.adapter.rxjava.HttpException

/**
 * Created by renansantos on 01/07/16.
 */
class ParseError(open val code:Int, open val error:String){

    companion object {
        open fun parseError(err: HttpException):ParseError{
            return Gson().fromJson(err.response().errorBody().string(), ParseError::class.java);
        }
    }
}