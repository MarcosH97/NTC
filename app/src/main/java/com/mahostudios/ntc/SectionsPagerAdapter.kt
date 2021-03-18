@file:Suppress("DEPRECATION")

package com.mahostudios.ntc

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val fragmentList: ArrayList<Fragment>,
    fm: FragmentManager
    ) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = fragmentList.size

    override fun getItem(position: Int): Fragment {
        if (position >= 0 && position < fragmentList.size)
            return fragmentList[position]
        return Fragment()
    }
}
