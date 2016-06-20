package com.hello.kotlin.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.hello.kotlin.app.R
import com.hello.kotlin.app.entity.Person
import com.hello.kotlin.app.http.client.ParsePersonClient
import com.hello.kotlin.app.http.factory.ClientFactory
import com.hello.kotlin.app.http.factory.ServiceFactory
import okhttp3.OkHttpClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)
        init()
    }

    private fun init() {
        save()
    }

    private fun save() {
        val service = ServiceFactory().service(ClientFactory().create()).create(ParsePersonClient::class.java)

        val person = Person(name = "Rennnan ${Random().nextInt()}" )

        service.save(person)
                .flatMap { person -> service.findById(person.id!!) }
                .filter { person -> true }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { person ->
                        (findViewById(R.id.name) as TextView).text = person.name
                    },
                    { error ->
                        Log.e("subscribe", error.message);
                    }
                )

    }
}