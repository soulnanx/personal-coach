package com.hello.kotlin.app.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.hello.kotlin.app.R

/**
 * Created by renansantos on 23/06/16.
 */

class PersonFragment() : Fragment() {

    @BindView(R.id.text_name)
    var text: View? = null

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
        ButterKnife.bind(view)

    }
}