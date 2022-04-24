package com.securelink.myanmarbeenchanted.data.models

import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Query


interface MyModel {
    fun getPopularList(
        onSuccess: (PopularListResponse) ->Unit,
        onError:(String) -> Unit
    )

    fun getUpcommingList(
        api_key :String,
        onSuccess: (UpcommingListResponse) ->Unit,
        onError:(String) -> Unit
    )

}