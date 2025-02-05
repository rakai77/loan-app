package com.example.samir_test.data.remote

import com.example.samir_test.data.remote.response.LoanResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("json/loans.json")
    suspend fun getData() : Response<LoanResponse>
}