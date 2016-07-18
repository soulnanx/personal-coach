package com.personal.coach.app.util

import android.util.Log
import okhttp3.Request

/**
 * Created by renan on 16/07/16.
 */
class LogHandling {

    companion object{
        open fun restLog(request: Request){
            Log.i("RETROFIT2-METHOD", request.method())
            Log.i("RETROFIT2-URL", request.url().toString())
            request.headers().toMultimap().map { header ->  Log.i("RETROFIT2-HEADERS", header.key.toString() + " : " + header.value.toString()) }

            if (request.body() != null) Log.i("RETROFIT2-BODY", request.body()?.toString())

        }

    }

}