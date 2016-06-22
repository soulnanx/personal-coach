package com.hello.kotlin.app.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hello.kotlin.app.R
import com.hello.kotlin.app.entity.Person

/**
 * Created by renan on 18/06/16.
 */
class PersonAdapter() : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    constructor(people:MutableList<Person>, onClickItem:View.OnClickListener) : super(){

    }


    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = people[position]

        holder.name.text = item.name

        with (holder.name) {
            tag = item
            setOnClickListener(null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_person, parent, false);
        return ViewHolder(v);
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.item_person_name) as TextView
    }

}


