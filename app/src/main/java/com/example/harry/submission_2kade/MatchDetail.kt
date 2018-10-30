package com.example.harry.submission_2kade

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.harry.submission_2kade.Api.Api
import com.example.harry.submission_2kade.Api.EndPoint
import com.example.harry.submission_2kade.Helper.database
import com.example.harry.submission_2kade.Model.EventDB
import com.example.harry.submission_2kade.Model.EventsItem
import com.example.harry.submission_2kade.Model.TeamDetail
import com.example.harry.submission_2kade.R.drawable.ic_add_to_favorites
import com.example.harry.submission_2kade.R.drawable.ic_added_to_favorites
import com.example.harry.submission_2kade.R.id.add_to_favorite
import com.example.harry.submission_2kade.R.menu.detail_menu
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.content_match_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiModeManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetail : AppCompatActivity() {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    lateinit var matchDetail: EventsItem ;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_match_detail)
        toolbar.title = "Match Detail"
        setSupportActionBar(toolbar)

        matchDetail = intent.getSerializableExtra("match") as EventsItem

        favoriteState()


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        if (matchDetail.intHomeScore == null && matchDetail.intAwayScore == null) {
            home_score.text = "?"
            away_score.text = "?"
        } else {
            home_score.text = matchDetail.intHomeScore.toString()
            away_score.text = matchDetail.intAwayScore.toString()
        }


        match_date.text = matchDetail.dateEvent
        getHomeTeamBedge(matchDetail.idHomeTeam.toString())
        getAwayTeamBedge(matchDetail.idAwayTeam.toString())

        home_goal_scorer.text = matchDetail.strHomeGoalDetails.toString()
        away_goal_scorer.text = matchDetail.strAwayGoalDetails.toString()

        home_forward.text = matchDetail.strHomeLineupForward.toString()
        away_forward.text = matchDetail.strAwayLineupForward.toString()

        home_midfilder.text = matchDetail.strHomeLineupMidfield.toString()
        away_midfilder.text = matchDetail.strAwayLineupMidfield.toString()

        home_defender.text = matchDetail.strHomeLineupDefense.toString()
        away_defender.text = matchDetail.strAwayLineupDefense.toString()

        home_goalkeeper.text = matchDetail.strHomeLineupGoalkeeper.toString()
        away_goalkeeper.text = matchDetail.strAwayLineupGoalkeeper.toString()

        home_subs.text = matchDetail.strHomeLineupSubstitutes.toString()
        away_subs.text = matchDetail.strAwayLineupSubstitutes.toString()


        favoriteState()

    }


    fun getHomeTeamBedge(teamID: String) {
        val api: EndPoint = Api.create()
        val callTeamDetail: Call<TeamDetail> = api.getTeamDetail(teamID)

        callTeamDetail.enqueue(object : Callback<TeamDetail> {
            override fun onFailure(call: Call<TeamDetail>, t: Throwable) {
                toast("Home Logo Get Failed")
            }

            override fun onResponse(call: Call<TeamDetail>, response: Response<TeamDetail>) {
                home_crest.let {
                    Picasso.get().load(response!!.body()!!.teams!![0]!!.strTeamBadge).into(home_crest)
                }
                toast("Home Logo Get Success")
            }

        })

    }


    fun getAwayTeamBedge(teamID: String) {
        val api: EndPoint = Api.create()
        val callTeamDetail: Call<TeamDetail> = api.getTeamDetail(teamID)

        callTeamDetail.enqueue(object : Callback<TeamDetail> {
            override fun onFailure(call: Call<TeamDetail>, t: Throwable) {
                toast("Away Logo Get Success")
            }

            override fun onResponse(call: Call<TeamDetail>, response: Response<TeamDetail>) {
                home_crest.let {
                    Picasso.get().load(response!!.body()!!.teams!![0]!!.strTeamBadge).into(away_crest)
                }
                toast("Away Logo Get Success")
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(EventDB.TABLE_FAVORITE, "(${EventDB.EVENT_ID} = ${matchDetail.idEvent.toString()})")
            }
            toast("Removed From DB")
        } catch (e: SQLiteConstraintException) {
            Log.d("DB", e.localizedMessage)
        }
    }

    private fun addToFavorite() {

        try {
            database.use {
                insert(EventDB.TABLE_FAVORITE,
                        EventDB.DATE_EVENT to matchDetail.dateEvent.toString(),
                        EventDB.HOME_TEAM to matchDetail.strHomeTeam.toString(),
                        EventDB.AWAY_TEAM to matchDetail.strAwayTeam.toString(),
                        EventDB.HOME_SCORE to matchDetail.intHomeScore.toString(),
                        EventDB.AWAY_SCORE to matchDetail.intAwayScore.toString(),
                        EventDB.EVENT_ID to matchDetail.idEvent.toString()

                )
            }
            toast("Data Saved")

        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(EventDB.TABLE_FAVORITE)
                    .where("(${EventDB.EVENT_ID} = ${matchDetail.idEvent})")
            val favorite = result.parseList(classParser<EventDB>())

            if (!favorite.isEmpty()){
                isFavorite = true
            }

            setFavorite()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

}
