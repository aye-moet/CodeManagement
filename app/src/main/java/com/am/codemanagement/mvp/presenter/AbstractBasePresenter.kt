package com.am.codemanagement.mvp.presenter

import com.am.codemanagement.mvp.view.BaseView


abstract class AbstractBasePresenter<T: BaseView> : BasePresenter<T> {

    var mView: T? = null

    override fun initPresenter(view: T) {
        mView = view
    }
}