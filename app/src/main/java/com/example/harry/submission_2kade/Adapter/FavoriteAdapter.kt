package com.example.harry.submission_2kade.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.harry.submission_2kade.Api.Api
import com.example.harry.submission_2kade.Api.EndPoint
import com.example.harry.submission_2kade.ComponentUI.FixturesItemUI
import com.example.harry.submission_2kade.MatchDetail
import com.example.harry.submission_2kade.Model.EventDB
import com.example.harry.submission_2kade.Model.EventsItem
import com.example.harry.submission_2kade.Model.Fixtures
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteAdapter(events:List<EventDB>,ctx: Context) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    val events:List<EventDB> = events
    val ctx = ctx

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(FixturesItemUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int {
        return events!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val viewHolder = p0
        val match = events!![p1]

        viewHolder.fixturesDate.text = match!!.DATE_EVENT
        viewHolder.homeTeamName.text = match!!.HOME_TEAM
        viewHolder.awayTeamName.text = match!!.AWAY_TEAM

        if (match.HOME_SCORE == "" && match.AWAY_SCORE == "") {
            viewHolder.fixturesScore.text = " ? - ? "
        } else {
            viewHolder.fixturesScore.text = " ${match!!.HOME_SCORE} - ${match!!.AWAY_SCORE} "
        }

        viewHolder.fixturesContainer.onClick {
            viewHolder.matchDetail(ctx,match)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var fixturesDate: TextView
        var homeTeamName: TextView
        var awayTeamName: TextView
        var fixturesScore: TextView
        var fixturesContainer: LinearLayout


        init {
            fixturesDate = itemView.findViewById(FixturesItemUI.fixturesDate)
            homeTeamName = itemView.findViewById(FixturesItemUI.homeTeamName)
            awayTeamName = itemView.findViewById(FixturesItemUI.awayTeamName)
            fixturesScore = itemView.findViewById(FixturesItemUI.fixturesScore)
            fixturesContainer = itemView.findViewById(FixturesItemUI.fixturesContainer)

        }

        fun matchDetail(ctx:Context,event:EventDB) {
            val apiInterface: EndPoint = Api.create()

            val fixturesRequest: Call<Fixtures> = apiInterface.getEventDetail(event.EVENT_ID)
            fixturesRequest.enqueue(object : Callback<Fixtures> {
                override fun onResponse(call: Call<Fixtures>, response: Response<Fixtures>) {
                    Toast.makeText(ctx, "SUCCESS", Toast.LENGTH_SHORT).show()
                    itemView.context.startActivity<MatchDetail>("match" to response.body()!!.events!![0])
                }

                override fun onFailure(call: Call<Fixtures>, t: Throwable) {
                    Toast.makeText(ctx, "FAILED", Toast.LENGTH_SHORT).show()
                }

            })


        }
    }

}