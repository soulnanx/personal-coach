package com.personal.coach.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.personal.coach.app.R
import com.personal.coach.app.entity.Day
import com.personal.coach.app.entity.Goal
import com.personal.coach.app.entity.Person
import java.util.*

/**
 * Created by renan on 18/06/16.
 */
class GoalAdapter(open val goals:List<Goal>) : RecyclerView.Adapter<GoalAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return goals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Goal = goals[position]
        holder.title.text = item.title
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_goal, parent, false);
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById(R.id.item_title) as TextView
    }

}


