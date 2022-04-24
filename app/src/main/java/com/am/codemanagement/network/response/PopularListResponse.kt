package com.am.codemanagement.network.response

import com.am.codemanagement.data.vos.RecommendedVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.google.gson.annotations.SerializedName


class PopularListResponse(
    @SerializedName ("page") val page: String,
    @SerializedName ("results") val results: MutableList<UpcommingMovieVO>,
    @SerializedName ("total_pages") val total_pages: String,
    @SerializedName ("total_results") val total_results: String
) {
}
