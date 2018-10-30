package com.example.harry.submission_2kade.Model

import com.google.gson.annotations.SerializedName

data class TeamDetail(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)