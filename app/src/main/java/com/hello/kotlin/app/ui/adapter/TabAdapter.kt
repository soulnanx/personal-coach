package com.hello.kotlin.app.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.hello.kotlin.app.ui.fragment.CarFragment
import com.hello.kotlin.app.ui.fragment.PersonFragment

/**
 * Created by renan on 25/06/16.
 */
class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {

        when(position){
            0 -> return PersonFragment.newInstance()
            1 -> return CarFragment.newInstance()
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "SECTION 1"
            1 -> return "SECTION 2"
        }
        return null
    }
}