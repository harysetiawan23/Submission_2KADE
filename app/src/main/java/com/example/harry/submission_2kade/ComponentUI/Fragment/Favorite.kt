package com.example.harry.submission_2kade.ComponentUI.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harry.submission_2kade.ComponentUI.FavoriteMatchFragmentView
import com.example.harry.submission_2kade.ComponentUI.NextMatchFragmentView
import org.jetbrains.anko.AnkoContext


class Favorite : Fragment() {


    companion object {

        fun newInstance(): Favorite {
            val fragment = Favorite()
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return FavoriteMatchFragmentView().createView(AnkoContext.Companion.create(container!!.context,container))
    }


}
