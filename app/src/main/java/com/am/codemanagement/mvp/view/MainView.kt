package com.am.codemanagement.mvp.view

import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse

interface MainView: BaseView {
    fun setUpAdapter()
    fun upcommingResponse(response: UpcommingListResponse)
    fun popularResponse(response: PopularListResponse)
}