package com.example.harry.submission_2kade.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.harry.submission_2kade.ComponentUI.Fragment.Favorite
import com.example.harry.submission_2kade.ComponentUI.Fragment.FootballLast
import com.example.harry.submission_2kade.ComponentUI.Fragment.FootballNext

class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> FootballLast.newInstance()
        1 -> FootballNext.newInstance()
        2 -> Favorite.newInstance()
        else -> null
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String = ""
        when (position) {
            0 -> title = "Latest Match"
            1 -> title = "Next Match"
            2 -> title = "Favorite"
        }
        return title
    }

}