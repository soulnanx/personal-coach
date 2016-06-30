package com.personal.coach.app.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.personal.coach.app.R
import com.personal.coach.app.entity.Repository
import com.personal.coach.app.entity.User

/**
 * Created by renan on 18/06/16.
 */
class RepositoryAdapter(context: Context, val resource: Int, repositories: List<Repository>) :
        ArrayAdapter<Repository>(context, resource, repositories) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val item = getItem(position)
        val view = LayoutInflater.from(context).inflate(resource, null)
        val holder = Holder(view)
        holder.textView.text = item.fullName
        return view
    }

    class Holder (v: View) {
        val textView = v.findViewById(R.id.list_item) as TextView
    }
}