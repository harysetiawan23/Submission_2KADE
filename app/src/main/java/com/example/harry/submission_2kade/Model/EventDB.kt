package com.example.harry.submission_2kade.Model

import com.google.gson.annotations.SerializedName

data class EventDB(
        val ID: Long,
        val DATE_EVENT: String,
        val HOME_TEAM: String,
        val AWAY_TEAM: String,
        val HOME_SCORE: String,
        val AWAY_SCORE: String,
        val EVENT_ID: String) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_SCHEDULE"
        const val ID: String = "ID_"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_ID: String = "EVENT_ID"


    }
}