package com.securelink.myanmarbeenchanted.data.models

import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import com.am.codemanagement.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MyModelImpl: BaseModel(), MyModel {
    override fun getPopularList(
        onSuccess: (PopularListResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = mNetworkApi.getPopularList("https://api.themoviedb.org/3/movie/popular?api_key=3875342a650d7bd12a1bc92bee310358")
        response.enqueue(object : Callback<PopularListResponse> {
            override fun onFailure(
                call: Call<PopularListResponse>,
                t: Throwable
            ) {
                onError(t.message.toString())
            }

            override fun onResponse(
                call: Call<PopularListResponse>,
                response: Response<PopularListResponse>
            ) {
                if (response.isSuccessful) {
                    val mResponse = response.body()
                    if (mResponse != null) {
                        onSuccess(mResponse)
                    } else {
                        onError(response.message())
                    }
                } else {
                    onError(response.message())
                }
            }

        })
    }

    override fun getUpcommingList(
        api_key: String,
        onSuccess: (UpcommingListResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val response = mNetworkApi.getUpcommingList(api_key)
        response.enqueue(object : Callback<UpcommingListResponse> {
            override fun onFailure(
                call: Call<UpcommingListResponse>,
                t: Throwable
            ) {
                onError(t.message.toString())
            }

            override fun onResponse(
                call: Call<UpcommingListResponse>,
                response: Response<UpcommingListResponse>
            ) {
                if (response.isSuccessful) {
                    val mResponse = response.body()
                    if (mResponse != null) {
                        onSuccess(mResponse)
                    } else {
                        onError(response.message())
                    }
                } else {
                    onError(response.message())
                }
            }

        })
    }

}