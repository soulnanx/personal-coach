package com.personal.coach.app.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.dayal.coach.app.ui.fragment.DayFragment
import com.dayal.coach.app.ui.fragment.GoalFragment
import com.personal.coach.app.R
import com.personal.coach.app.ui.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_tab.*
import java.util.*

class TabActivity : AppCompatActivity() {

    private val icons = intArrayOf(android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_lock_lock, android.R.drawable.ic_dialog_info)
    private val TAB_GOAL = 0
    private val TAB_DAY = 1

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
        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
        setupTabIcons()
    }

    private fun setupTabIcons() {
        tabs.getTabAt(TAB_GOAL)!!.setIcon(icons[TAB_GOAL])
        tabs.getTabAt(TAB_DAY)!!.setIcon(icons[TAB_DAY])
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(GoalFragment(), "GOAL")
        adapter.addFrag(DayFragment(), "DAY")
        viewPager.adapter = adapter
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tab, menu)
        return true
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
