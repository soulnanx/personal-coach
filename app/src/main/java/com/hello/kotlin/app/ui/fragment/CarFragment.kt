package com.hello.kotlin.app.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hello.kotlin.app.R
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.fragment_person.*

/**
 * Created by renan on 25/06/16.
 */
class CarFragment : Fragment() {

    companion object {
        fun newInstance(): CarFragment {
            return CarFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater!!.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setToolbar()
    }

    private fun setToolbar() {
        activity.toolbar.title = "frag2"
    }
}