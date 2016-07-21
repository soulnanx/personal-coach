package com.personal.coach.app.util

import android.app.ProgressDialog
import android.content.Context

/**
 * Created by renan on 25/06/16.
 */

class LoadingDialog{
    companion object{
        fun show(ctx:Context, msg:String = "Loading. Please wait..."):ProgressDialog{
            return ProgressDialog.show(ctx, "", msg, true)
        }
    }
}
