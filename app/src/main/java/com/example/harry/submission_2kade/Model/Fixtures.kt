package com.example.harry.submission_2kade.Model

import com.google.gson.annotations.SerializedName


data class Fixtures(

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null
)