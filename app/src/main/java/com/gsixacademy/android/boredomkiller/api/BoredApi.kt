package com.gsixacademy.android.boredomkiller.api

import com.gsixacademy.android.boredomkiller.models.ActivityResponse
import retrofit2.Call
import retrofit2.http.GET

interface BoredApi {

    @GET ("/api/activity/")
    fun getRandomActivity() : Call<ActivityResponse>
}