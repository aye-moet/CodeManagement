package com.am.codemanagement.mvp.view

interface BaseView {
    fun init()
    fun setUpLoadingDialog()
    fun checkNetwork()
    fun listener()
    fun showLoading()
    fun hideLoading()
    fun showErrorMessage(message:String)
}