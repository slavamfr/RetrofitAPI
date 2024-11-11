package com.faiz.retrofitapi.network

import com.faiz.retrofitapi.model.Data
import com.faiz.retrofitapi.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users?page=2") // Assuming this is the correct endpoint.
    fun getallusers(): Call<Users>
}