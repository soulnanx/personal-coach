package com.dayal.coach.app.ui.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.personal.coach.app.R
import com.personal.coach.app.entity.Goal
import com.personal.coach.app.http.client.GoalClient
import com.personal.coach.app.http.factory.ClientFactory
import com.personal.coach.app.http.factory.ServiceFactory
import com.personal.coach.app.ui.activity.AddGoalActivity
import com.personal.coach.app.ui.adapter.GoalAdapter
import com.personal.coach.app.util.LoadingDialog
import com.personal.coach.app.util.NavigateUtils
import kotlinx.android.synthetic.main.fragment_goal.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by renansantos on 23/06/16.
 */

class GoalFragment() : Fragment() {

    companion object {
        fun newInstance(): GoalFragment {
            return GoalFragment()
        }

        private var loading: ProgressDialog? = null
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setEvents()
        loadValues()
    }

    private fun loadValues() {
        showLoading()
        fragment_goal_recyclerView.layoutManager = LinearLayoutManager(activity)
        serviceFindAll()
    }

    private fun setEvents() {
        swipe.setOnRefreshListener { onSwipeList() }
        fragment_goal_fab.setOnClickListener { navigateToAddGoal() }
    }

    private fun onSwipeList() {
        swipe.isRefreshing = true
        serviceFindAll()
    }

    private fun navigateToAddGoal() {
        NavigateUtils.navigateForResultTo(this@GoalFragment.activity, AddGoalActivity::class.java, AddGoalActivity.REQUEST_ADD_GOAL)
    }

    private fun showLoading() {
        if (Companion.loading == null) {
            Companion.loading = LoadingDialog.show(activity)
        } else {
            Companion.loading!!.show()
        }
    }

    private fun hideLoading() {
        if (Companion.loading != null && Companion.loading!!.isShowing) {
            Companion.loading!!.dismiss()
        }
    }

    private fun serviceFindAll() {
        swipe.isRefreshing = false
        showLoading()

        val service = ServiceFactory().service(ClientFactory().create()).create(GoalClient::class.java)
        service.findAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> setList(result.results!!) }, { err -> showError(err.message!!) })
    }

    private fun showError(err: String = "Desconhecido") {
        Toast.makeText(activity, err, Toast.LENGTH_SHORT).show()
        hideLoading()
    }

    private fun setList(goals: List<Goal> = ArrayList<Goal>()) {
        with(fragment_goal_recyclerView) {
            adapter = GoalAdapter(goals)
            hasFixedSize()
        }
        hideLoading()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AddGoalActivity.REQUEST_ADD_GOAL && resultCode == AddGoalActivity.RESULT_ADD_GOAL) {
            loadValues()
        }
    }
}