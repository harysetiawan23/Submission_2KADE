package com.example.harry.submission_2kade.ComponentUI

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.R.attr.colorAccent
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import com.example.harry.submission_2kade.Adapter.FixturesAdapter
import com.example.harry.submission_2kade.Api.Api
import com.example.harry.submission_2kade.Api.EndPoint
import com.example.harry.submission_2kade.Model.Fixtures
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchFragmentView : AnkoComponent<ViewGroup> {
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout


    @SuppressLint("ResourceType")
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {


        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(android.R.color.holo_green_light,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
            swipeRefresh.isRefreshing = true
            getData(ui.ctx)

            swipeRefresh.setOnRefreshListener {
                object : SwipeRefreshLayout.OnRefreshListener {
                    override fun onRefresh() {
                        getData(ui.ctx)
                    }

                }
            }


        }


    }


    fun getData(ctx: Context) {
        val apiInterface: EndPoint = Api.create()

        val fixturesRequest: Call<Fixtures> = apiInterface.getNextFixtures("4328")
        fixturesRequest.enqueue(object : Callback<Fixtures> {
            override fun onResponse(call: Call<Fixtures>, response: Response<Fixtures>) {
                listTeam.adapter = FixturesAdapter(response!!.body())
                Toast.makeText(ctx, "SUCCESS", Toast.LENGTH_SHORT).show()
                swipeRefresh.isRefreshing = false
            }

            override fun onFailure(call: Call<Fixtures>, t: Throwable) {
                Toast.makeText(ctx, "FAILED", Toast.LENGTH_SHORT).show()
                swipeRefresh.isRefreshing = false
            }

        })
    }

}