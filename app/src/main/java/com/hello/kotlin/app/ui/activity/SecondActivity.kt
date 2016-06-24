package com.hello.kotlin.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.stream.JsonReader
import com.hello.kotlin.app.R
import com.hello.kotlin.app.adapter.PersonAdapter
import com.hello.kotlin.app.adapter.RepositoryAdapter
import com.hello.kotlin.app.entity.Person
import com.hello.kotlin.app.http.client.ParsePersonClient
import com.hello.kotlin.app.http.factory.ClientFactory
import com.hello.kotlin.app.http.factory.ServiceFactory
import okhttp3.OkHttpClient
import org.json.JSONObject
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
        getAll()
    }

    private fun getAll() {
        val service = ServiceFactory().service(ClientFactory().create()).create(ParsePersonClient::class.java)
        service.findAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { dto ->
                        val adapter = PersonAdapter(people = dto.results!!)
                        val recyclerView = findViewById(R.id.list_person) as RecyclerView

                        recyclerView.layoutManager = LinearLayoutManager(this)
                        recyclerView.hasFixedSize()
                        recyclerView.adapter = adapter

                    },
                    { error ->
                        Log.e("subscribe", error.message);
                    }
                )

    }
}

data class Person2(open var name:String? = null, open var age:Int = 0, open var address:Address? = null){
}

data class Address(open var title:String? = null){
}