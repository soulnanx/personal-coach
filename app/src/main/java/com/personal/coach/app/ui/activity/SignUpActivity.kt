package com.personal.coach.app.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
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
import kotlinx.android.synthetic.main.activity_signup.*
import org.json.JSONObject
import retrofit2.adapter.rxjava.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        init()
    }

    private fun init() {
        setValues()
        loadValues()
    }

    private fun setValues() {
        activity_sign_up_btn_sign_up.setOnClickListener({ view -> onClickSignUp() })
    }

    private fun onClickSignUp() {
        val user = User(
                username = activity_sign_up_edt_username.text.toString(),
                password = activity_sign_up_edt_password.text.toString(),
                email = activity_sign_up_edt_email.text.toString())

        if (validatePassword()){
            if (validateUser(user)) {
                serviceLogin(user)
            } else {
                showValidationError()
            }
        } else {
            showValidationPasswordError()
        }

    }

    private fun showValidationPasswordError() {
        Toast.makeText(this@SignUpActivity, "As senhas não são as mesmas", Toast.LENGTH_SHORT).show()
    }

    private fun validatePassword(): Boolean {
        return activity_sign_up_edt_password.text.toString().equals(
                activity_sign_up_edt_retype_password.text.toString())
    }

    private fun showValidationError() {
        Toast.makeText(this@SignUpActivity, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
    }

    private fun serviceLogin(user: User) {
        val service = ServiceFactory().service(ClientFactory().create()).create(UserClient::class.java)

        service.signUp(user)
                .flatMap { user -> service.findById(user.id!!) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user -> showUser(user) }, { err -> showError(err as HttpException) })
    }

    private fun loadValues() {

    }

    private fun validateUser(user: User): Boolean {
        return user.username.isNotBlank()
                && user.password.isNotBlank()
                && user.email.isNotBlank()
    }

    private fun showError(err: HttpException) {
//        val error: ParseError = ParseError.Companion.parseError(err)
        var message: String
//        when (error.code) {
//            202 -> message = "Já existe um usuario com esse nome"
//            125 -> message = "Email invalido"
//            203 -> message = "Esse email já está vinculado a uma conta"
//            else -> message = "Erro desconhecido"
//        }
//
        Toast.makeText(this@SignUpActivity, err.response().errorBody().string(), Toast.LENGTH_SHORT).show()
    }

    private fun showUser(user: User) {
        NavigateUtils.navigateTo(this@SignUpActivity, TabActivity::class.java, true)
    }
}