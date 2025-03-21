package com.example.appestagionovo.api

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {
    @GET("estagios")
    fun getEstagios() : Call<JsonArray>
}