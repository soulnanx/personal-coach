package com.hello.kotlin.app.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.hello.kotlin.app.R
import com.hello.kotlin.app.ui.adapter.RepositoryAdapter
import com.hello.kotlin.app.http.client.GitHubClient
import com.hello.kotlin.app.http.factory.ServiceFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    fun activity(): Activity = this

    fun click(view: View) {
        Toast.makeText(activity(), "Amor", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {

        loadValues()
    }

    private fun loadValues() {
        val service = ServiceFactory().service().create(GitHubClient::class.java)

        service.getUser("soulnanx")
                .flatMap { user ->
                    //(findViewById(R.id.user_name_txt) as TextView).text = user.name
                    service.repositories(user.reposUrl!!.path) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { repositories ->
                    val adapter = RepositoryAdapter(activity(), R.layout.list_item, repositories)
                    val listView = findViewById(R.id.list_repositories) as ListView
                    listView.adapter = adapter
                }
    }
}