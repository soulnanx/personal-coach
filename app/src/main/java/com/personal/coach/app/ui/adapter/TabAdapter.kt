package com.personal.coach.app.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dayal.coach.app.ui.fragment.DayFragment
import com.dayal.coach.app.ui.fragment.GoalFragment

/**
 * Created by renan on 25/06/16.
 */
class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when(position){
            0 -> return GoalFragment.newInstance()
            1 -> return DayFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Goal"
            1 -> return "Score"
        }
        return null
    }
}