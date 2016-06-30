package com.personal.coach.app.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.dayal.coach.app.ui.fragment.DayFragment
import com.personal.coach.app.ui.fragment.CarFragment
import com.personal.coach.app.ui.fragment.PersonFragment

/**
 * Created by renan on 25/06/16.
 */
class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when(position){
            0 -> return DayFragment.newInstance()
            1 -> return PersonFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Day"
            1 -> return "Person"
        }
        return null
    }
}