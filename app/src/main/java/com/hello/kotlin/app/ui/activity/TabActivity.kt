package com.hello.kotlin.app.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.hello.kotlin.app.R
import com.hello.kotlin.app.ui.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_tab.*

class TabActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: TabAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        init()
    }

    private fun init() {
        setToolbar()
        setTabs()
    }

    private fun setTabs() {
        mSectionsPagerAdapter = TabAdapter(supportFragmentManager)
        container!!.adapter = mSectionsPagerAdapter
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tab, menu)
        return true
    }
}
