package com.example.harry.submission_2kade.Api

import com.example.harry.submission_2kade.Model.Fixtures
import com.example.harry.submission_2kade.Model.TeamDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoint {

    @GET("eventsnextleague.php?")
    fun getNextFixtures(@Query("id") leagueID:String): Call<Fixtures>

    @GET("eventspastleague.php?")
    fun getLastFixtures(@Query("id") leagueID:String): Call<Fixtures>

    @GET("lookupteam.php?")
    fun getTeamDetail(@Query("id") leagueID:String): Call<TeamDetail>

    @GET("lookupevent.php?")
    fun getEventDetail(@Query("id") leagueID:String): Call<Fixtures>
}