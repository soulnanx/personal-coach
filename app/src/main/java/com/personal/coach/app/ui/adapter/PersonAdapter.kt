package com.personal.coach.app.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.personal.coach.app.R
import com.personal.coach.app.entity.Person
import java.util.*

/**
 * Created by renan on 18/06/16.
 */
class PersonAdapter(open val people:List<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return people.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:Person = people[position]
        holder.name.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v:View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_person, parent, false);
        return ViewHolder(v);
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById(R.id.item_person_name) as TextView
    }

}


