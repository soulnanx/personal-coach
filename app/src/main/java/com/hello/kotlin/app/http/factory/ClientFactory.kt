package com.hello.kotlin.app.http.factory

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

/**
 * Created by renan on 19/06/16.
 */
class ClientFactory {

    open fun create() : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(UrlInterceptor()).build()
    }

    class UrlInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response? {
            val r = chain.request()
            val request = r.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-Parse-Application-Id", "api.id")
                    .addHeader("X-Parse-Master-Key", "888")
                    .build()
            return chain.proceed(request)
        }
    }
}