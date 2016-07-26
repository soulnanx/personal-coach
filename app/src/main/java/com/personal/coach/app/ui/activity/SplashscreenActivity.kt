package com.personal.coach.app.ui.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.personal.coach.app.R
import com.personal.coach.app.entity.User
import com.personal.coach.app.util.NavigateUtils

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        init()
    }

    private fun init() {
        Handler().postDelayed({ navigate() }, 2000)
    }

    private fun navigate() {
        when (User.isLogged(this@SplashScreenActivity)){
            true -> NavigateUtils.navigateTo(this@SplashScreenActivity, TabActivity::class.java, true)
            false -> NavigateUtils.navigateTo(this@SplashScreenActivity, LoginActivity::class.java, true)
        }
    }
}