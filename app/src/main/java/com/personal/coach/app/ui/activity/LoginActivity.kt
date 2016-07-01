package com.personal.coach.app.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.personal.coach.app.R
import com.personal.coach.app.entity.User
import com.personal.coach.app.http.client.UserClient
import com.personal.coach.app.http.factory.ClientFactory
import com.personal.coach.app.ui.adapter.RepositoryAdapter
import com.personal.coach.app.http.factory.ServiceFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

    }

    private fun init() {
        loadValues()
    }

    private fun loadValues() {
        val service = ServiceFactory().service(ClientFactory().create()).create(UserClient::class.java)

        service.signUp(User(username = "renan", password = "123@mudar", email = "renan@gmail.com"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user -> showUser(user)

                }, {err -> showError(err)})
    }

    private fun showError(err: Throwable?) {
        err!!.message.toString()
    }

    private fun showUser(user:User) {
        user.toString()
    }
}