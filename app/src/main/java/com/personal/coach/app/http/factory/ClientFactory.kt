package com.personal.coach.app.http.factory

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Created by renan on 19/06/16.
 */
class ClientFactory {

    open fun create() : OkHttpClient{
        return OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(UrlInterceptor()).build()
    }

    class UrlInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response? {
            val r = chain.request()
            val request = r.newBuilder()
                    .addHeader("X-Parse-Application-Id", "app.id")
                    .addHeader("X-Parse-REST-API-Key", "888")
                    .build()


            Log.i("RETROFIT2-METHOD", request.method())
            Log.i("RETROFIT2-URL", request.url().toString())
            request.headers().toMultimap().map { header ->  Log.i("RETROFIT2-HEADERS", header.key.toString() + " : " + header.value.toString()) }

            if (request.body() != null) Log.i("RETROFIT2-BODY", request.body()?.toString())


            return chain.proceed(request)
        }
    }
}