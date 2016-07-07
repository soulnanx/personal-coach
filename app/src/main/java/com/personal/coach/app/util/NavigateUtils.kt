package com.personal.coach.app.util

import android.app.Activity
import android.content.Intent

/**
 * Created by renan on 02/07/16.
 */
class NavigateUtils {

    companion object{

        fun navigateForResultTo(activity: Activity, destiny: Class<*>, request: Int){
            activity.startActivityForResult(Intent(activity, destiny), request)
        }

        fun navigateTo(activity: Activity, destiny: Class<*>, clearStack: Boolean) {
            val i = Intent(activity, destiny)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(i)

            if (clearStack) {
                activity.finish()
            }
        }

    }

}