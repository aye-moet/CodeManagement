package com.am.codemanagement.mvp.presenter

interface MainPresenter {
    fun checkNetwork()
    fun showLoading()
    fun fetchPopularData()
    fun fetchUpcommingData()
}