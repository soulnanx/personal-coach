package com.personal.coach.app.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dayal.coach.app.ui.fragment.GoalFragment
import com.personal.coach.app.R
import com.personal.coach.app.entity.Goal
import com.personal.coach.app.http.client.GoalClient
import com.personal.coach.app.http.factory.ClientFactory
import com.personal.coach.app.http.factory.ServiceFactory
import com.personal.coach.app.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_add_goal.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by renan on 05/07/16.
 */
class AddGoalActivity : AppCompatActivity() {

    companion object{
        open val RESULT_ADD_GOAL = 3001
        open val REQUEST_ADD_GOAL = 2001
    }

    private var loading: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_goal)
        init()
    }

    private fun init() {
        loadValues()
        setEvents()
    }

    private fun setEvents() {
        addGoalBtnAddGoal.setOnClickListener {onClickAddGoal()}
    }

    private fun onClickAddGoal() {
        if (validateFields()){
            serviceAddGoal(buildGoal())
        }
    }

    private fun validateFields(): Boolean {
        return addGoalTitle.text.isNotBlank() &&
                addGoalMessage.text.isNotBlank()
    }

    private fun buildGoal(): Goal {
        return Goal(title = addGoalTitle.text.toString(),
                message = addGoalTitle.text.toString(),
                annoyingMessage = "try")
    }

    private fun loadValues() {
    }

    private fun showLoading() {
        if (loading == null) {
            loading = LoadingDialog.show(this@AddGoalActivity)
        } else {
            loading!!.show()
        }
    }

    private fun hideLoading() {
        if (loading != null && loading!!.isShowing) {
            loading!!.dismiss()
        }
    }

    private fun serviceAddGoal(goal: Goal) {
        showLoading()

        val service = ServiceFactory().service(ClientFactory().create()).create(GoalClient::class.java)
        service.save(goal)
                .doOnError { err -> onError(err.message!!) }
                .flatMap { goal -> service.findById(goal.id!!)}
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({goal -> onSuccess(goal)}, {err -> onError(err.message!!)})
    }

    private fun onError(message: String) {
        hideLoading()
        Toast.makeText(this@AddGoalActivity, message, Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(goal: Goal) {
        hideLoading()
        setResult(RESULT_ADD_GOAL)
        finish()
    }
}