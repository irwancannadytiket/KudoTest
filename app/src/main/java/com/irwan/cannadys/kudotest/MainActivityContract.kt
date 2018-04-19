package com.irwan.cannadys.kudotest

import com.irwan.cannadys.kudotest.model.LoginResponse

/**
 * Created by irwancannady on 4/17/18.
 */
interface MainActivityContract {

    interface View : BaseView<Presenter>{
        fun showHideProgress(status: Boolean)
        fun goToProfilePage(loginResponse: LoginResponse?)
        fun toast(message : String?)
        fun showSnackBar(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter: BasePresenter<View>{
        fun getLogin(username:String, password:String)
    }
}