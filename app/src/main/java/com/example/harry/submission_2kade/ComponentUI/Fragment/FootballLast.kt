package com.example.harry.submission_2kade.ComponentUI.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harry.submission_2kade.ComponentUI.LatestMatchFragmentView
import org.jetbrains.anko.AnkoContext


class FootballLast : Fragment() {


    companion object {

        fun newInstance(): FootballLast {
            val fragment = FootballLast()
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return LatestMatchFragmentView().createView(AnkoContext.create(container!!.context,container))
    }




}
