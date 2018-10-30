package com.example.harry.submission_2kade.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.harry.submission_2kade.ComponentUI.FixturesItemUI
import com.example.harry.submission_2kade.MatchDetail
import com.example.harry.submission_2kade.Model.EventsItem
import com.example.harry.submission_2kade.Model.Fixtures
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity

class FixturesAdapter(fixtures: Fixtures?) : RecyclerView.Adapter<FixturesAdapter.ViewHolder>() {
    val events = fixtures!!.events

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(FixturesItemUI().createView(AnkoContext.create(p0.context, p0)))
    }

    override fun getItemCount(): Int {
        return events!!.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val viewHolder = p0
        val match = events!![p1]

        viewHolder.fixturesDate.text = match!!.dateEvent
        viewHolder.homeTeamName.text = match!!.strHomeTeam
        viewHolder.awayTeamName.text = match!!.strAwayTeam

        if (match.intHomeScore == null && match.intAwayScore == null) {
            viewHolder.fixturesScore.text = " ? - ? "
        } else {
            viewHolder.fixturesScore.text = " ${match!!.intHomeScore} - ${match!!.intAwayScore} "
        }

        viewHolder.fixturesContainer.onClick {
            viewHolder.matchDetail(match)
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

        fun matchDetail(eventItems: EventsItem) {
            itemView.context.startActivity<MatchDetail>("match" to eventItems)
        }
    }

}