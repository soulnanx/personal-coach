package com.hello.kotlin.app.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hello.kotlin.app.R
import com.hello.kotlin.app.entity.Person
import com.hello.kotlin.app.http.client.PersonClient
import com.hello.kotlin.app.http.factory.ClientFactory
import com.hello.kotlin.app.http.factory.ServiceFactory
import com.hello.kotlin.app.ui.adapter.PersonAdapter
import com.hello.kotlin.app.util.LoadingDialog
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.fragment_person.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by renansantos on 23/06/16.
 */

class PersonFragment() : Fragment() {

    private var loading: ProgressDialog? = null

    companion object {
        fun newInstance(): PersonFragment {
            return PersonFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater!!.inflate(R.layout.fragment_person, container, false)
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
        btnAddUser.setOnClickListener { view -> onClickAddUser() }
        swipeRefresh.setOnRefreshListener { onSwipeList() }
    }

    private fun onSwipeList() {
        swipeRefresh.isRefreshing = true
        serviceFindAll()
    }

    private fun onClickAddUser() {
        serviceAddUser()
    }

    private fun serviceAddUser() {
        loading!!.show()

        val service = ServiceFactory().service(ClientFactory().create()).create(PersonClient::class.java)
        service.save(Person(name = "Jenifer ${Random().nextFloat()}"))
        .flatMap { person -> service.findById(person.id!!) }
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({person -> showPerson(person)}, {err -> showError(err.message!!)})
    }

    private fun showPerson(person:Person) {
        Toast.makeText(activity, person.name, Toast.LENGTH_SHORT).show()
        serviceFindAll()
    }

    private fun serviceFindAll() {
        swipeRefresh.isRefreshing = false
        loading!!.show()

        val service = ServiceFactory().service(ClientFactory().create()).create(PersonClient::class.java)
        service.findAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ peopleDTO -> setList(peopleDTO.results!!) }, { err -> showError(err.message!!) })
    }

    private fun showError(err:String = "Desconhecido") {
        Toast.makeText(activity, err, Toast.LENGTH_SHORT).show()
        loading!!.dismiss()
    }

    private fun setList(people: List<Person> = ArrayList<Person>()) {
        with(list_person){
            adapter = PersonAdapter(people)
            layoutManager = LinearLayoutManager(activity)
            hasFixedSize()
        }
        loading!!.dismiss()
    }

    private fun setToolbar() {
        activity.toolbar.title = "frag1"
    }
}