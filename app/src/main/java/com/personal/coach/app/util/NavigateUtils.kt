package com.personal.coach.app.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.personal.coach.app.ui.activity.TabActivity
import kotlin.reflect.KClass

/**
 * Created by renan on 02/07/16.
 */
class NavigateUtils {

    fun navigateTo(activity: Activity, destiny: Class<*>, clearStack: Boolean) {
        val i = Intent(activity, destiny)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        activity.startActivity(i)

        if (clearStack) {
            activity.finish()
        }
    }

}