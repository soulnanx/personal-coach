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
import com.personal.coach.app.entity.Day
import com.personal.coach.app.entity.Goal
import com.personal.coach.app.http.client.DayClient
import com.personal.coach.app.http.client.GoalClient
import com.personal.coach.app.http.factory.ClientFactory
import com.personal.coach.app.http.factory.ServiceFactory
import com.personal.coach.app.ui.activity.AddGoalActivity
import com.personal.coach.app.ui.adapter.DayAdapter
import com.personal.coach.app.util.LoadingDialog
import com.personal.coach.app.util.NavigateUtils
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_goal.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by renansantos on 23/06/16.
 */

class GoalFragment() : Fragment() {

    private var loading: ProgressDialog? = null

    companion object {
        fun newInstance(): GoalFragment {
            return GoalFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater!!.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setToolbar()
        setEvents()
        loadValues()
    }

    private fun loadValues() {
        loading = LoadingDialog.show(activity)
        serviceFindAll()
    }

    private fun setEvents() {
        recyclerView.setOnClickListener { view -> onClickAddGoal() }
        swipe.setOnRefreshListener { onSwipeList() }
    }

    private fun onSwipeList() {
        swipeRefreshDay.isRefreshing = true
        serviceFindAll()
    }

    private fun onClickAddGoal() {
        navigateToAddGoal()
    }

    private fun navigateToAddGoal() {
        NavigateUtils.navigateForResultTo(this@GoalFragment.activity, AddGoalActivity::class.java, AddGoalActivity.REQUEST_ADD_GOAL)
    }

    private fun serviceFindAll() {
        swipeRefreshDay.isRefreshing = false
        loading!!.show()

        val service = ServiceFactory().service(ClientFactory().create()).create(DayClient::class.java)
        service.findAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> setList(result.results!!) }, { err -> showError(err.message!!) })
    }

    private fun showError(err:String = "Desconhecido") {
        Toast.makeText(activity, err, Toast.LENGTH_SHORT).show()
        loading!!.dismiss()
    }

    private fun setList(people: List<Day> = ArrayList<Day>()) {
        with(list_day){
            adapter = DayAdapter(people)
            layoutManager = LinearLayoutManager(activity)
            hasFixedSize()
        }
        loading!!.dismiss()
    }

    private fun setToolbar() {
        activity.toolbar.title = "dias"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AddGoalActivity.REQUEST_ADD_GOAL && resultCode == AddGoalActivity.RESULT_ADD_GOAL) {
            loadValues()
        }
    }
}