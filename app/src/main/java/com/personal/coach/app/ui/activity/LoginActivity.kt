package com.personal.coach.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.personal.coach.app.R
import com.personal.coach.app.dto.ParseError
import com.personal.coach.app.entity.User
import com.personal.coach.app.http.client.UserClient
import com.personal.coach.app.http.factory.ClientFactory
import com.personal.coach.app.http.factory.ServiceFactory
import com.personal.coach.app.util.NavigateUtils
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.adapter.rxjava.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }

    private fun init() {
        setValues()
        loadValues()
    }

    private fun setValues() {
        activity_login_btn_login.setOnClickListener { onClickLogin() }
        activity_login_btn_sign_up.setOnClickListener { onClickSignUp() }
    }

    private fun onClickSignUp() {
        NavigateUtils.navigateTo(this@LoginActivity, SignUpActivity::class.java, false)
    }

    private fun onClickLogin() {
        val user = User(
                username = activity_login_edt_username.text.toString(),
                password = activity_login_edt_password.text.toString())

        if (validateUser(user)) {
            serviceLogin(user)
        } else {
            showValidationError()
        }

    }

    private fun showValidationError() {
        Toast.makeText(this@LoginActivity, "Usuário ou senhas inválidos!", Toast.LENGTH_SHORT).show()
    }

    private fun serviceLogin(user: User) {
        val service = ServiceFactory().service(ClientFactory().create()).create(UserClient::class.java)

        service.login(user.username, user.password)
                .flatMap { user -> service.findById(user.id) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe({ user -> showUser(user) }, { err -> onError(err) })
    }

    private fun loadValues() {

    }

    private fun onError(message: Throwable) {
        Toast.makeText(this@LoginActivity, message.message, Toast.LENGTH_SHORT).show()
    }

    private fun validateUser(user: User): Boolean {
        return user.username.isNotBlank() && user.password.isNotBlank()
    }

    private fun showError(err: HttpException) {
        val error: ParseError = ParseError.Companion.parseError(err)
        var message: String
        when (error.code) {
            202 -> message = "Já existe um usuario com esse nome"
            125 -> message = "Email invalido"
            203 -> message = "Esse email já está vinculado a uma conta"
            else -> message = "Tente novamente"
        }

        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun showUser(user: User) {
        user.save(this@LoginActivity)
        NavigateUtils.navigateTo(this@LoginActivity, TabActivity::class.java, true)
    }
}