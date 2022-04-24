package com.am.codemanagement.network.response

import com.am.codemanagement.data.vos.DateVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.google.gson.annotations.SerializedName


class UpcommingListResponse(
    @SerializedName ("dates") val dates: DateVO,
    @SerializedName ("page") val page: String,
    @SerializedName ("results") val results: MutableList<UpcommingMovieVO>,
    @SerializedName ("total_pages") val total_pages: Int,
    @SerializedName ("total_results") val total_results: Int,
) {
}
