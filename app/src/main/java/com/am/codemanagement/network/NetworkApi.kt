package com.am.codemanagement.network

import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import com.am.codemanagement.utils.upcomming_url
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface NetworkApi {
    @GET()
    fun getPopularList(
        @Url url: String
    ): Call<PopularListResponse>

    @GET(upcomming_url)
    fun getUpcommingList(
        @Query("api_key") api_key: String
    ): Call<UpcommingListResponse>
}