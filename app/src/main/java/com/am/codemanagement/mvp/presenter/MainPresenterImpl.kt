package com.am.codemanagement.mvp.presenter

import com.am.codemanagement.mvp.view.MainView
import com.am.codemanagement.utils.API_KEY
import com.securelink.myanmarbeenchanted.data.models.MyModel
import com.securelink.myanmarbeenchanted.data.models.MyModelImpl


class MainPresenterImpl: MainPresenter, AbstractBasePresenter<MainView>() {
    private val mModel: MyModel = MyModelImpl
    override fun onStart() {

    }

    override fun onCreate() {

    }

    override fun onCreateView() {
        mView?.init()
    }

    override fun onViewCreated() {
        mView?.setUpLoadingDialog()
        mView?.setUpAdapter()
        mView?.checkNetwork()
        mView?.listener()
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {
        mView = null
    }

    override fun checkNetwork() {
        mView?.checkNetwork()
    }

    override fun showLoading() {
        mView?.showLoading()
    }

    override fun fetchPopularData() {
        mModel.getPopularList(
            onSuccess = {
                mView?.popularResponse(it)
                mView?.hideLoading()
            }, onError = {
                mView?.showErrorMessage(it)
                mView?.hideLoading()
            }
        )
    }

    override fun fetchUpcommingData() {
        mModel.getUpcommingList(
            API_KEY,
            onSuccess = {
                mView?.upcommingResponse(it)
                mView?.hideLoading()
            }, onError = {
                mView?.showErrorMessage(it)
                mView?.hideLoading()
            }
        )
    }

}