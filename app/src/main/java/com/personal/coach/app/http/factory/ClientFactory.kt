package com.personal.coach.app.http.factory

import com.personal.coach.app.BuildConfig
import com.personal.coach.app.constants.ConstantsParse
import com.personal.coach.app.util.LogHandling
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Created by renan on 19/06/16.
 */
class ClientFactory {

    fun create() : OkHttpClient{
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(UrlInterceptor()).build()
    }

    class UrlInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response? {
            val request = chain.request().newBuilder()
                    .addHeader(ConstantsParse.APP_ID, BuildConfig.APPLICATION_ID)
                    .addHeader(ConstantsParse.REST_API_KEY, BuildConfig.PARSE_API_KEY)
                    .build()

            LogHandling.restLog(request)

            return chain.proceed(request)
        }
    }
}


