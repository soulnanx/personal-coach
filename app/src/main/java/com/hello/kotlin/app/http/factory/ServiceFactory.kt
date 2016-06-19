package com.hello.kotlin.app.http.factory

import com.hello.kotlin.app.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by renan on 18/06/16.
 */
class ServiceFactory{
//TODO juntar metodos
    fun service(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .build()
    }

    fun service(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.API_ENDPOINT)
                .build()
    }


}