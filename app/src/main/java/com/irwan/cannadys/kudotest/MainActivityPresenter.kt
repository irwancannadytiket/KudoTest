package com.irwan.cannadys.kudotest

import android.accounts.NetworkErrorException
import com.irwan.cannadys.kudotest.commons.BaseSchedulerProvider
import com.irwan.cannadys.kudotest.commons.CoreAppRepository
import com.irwan.cannadys.kudotest.model.LoginResponse
import rx.Subscriber
import java.net.SocketTimeoutException

/**
 * Created by irwancannady on 4/17/18.
 */
class MainActivityPresenter(private val coreAppRepository: CoreAppRepository,
                            private val baseSchedulerProvider: BaseSchedulerProvider) : MainActivityContract.Presenter {

    private var views: MainActivityContract.View? = null

    override fun bind(view: MainActivityContract.View) {
        views = view
    }

    override fun unbind() {
        views = null
    }

    override fun getLogin(username: String, password: String) {
        views?.showHideProgress(true)
        coreAppRepository.getLogin(username, password)
                .subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui())
                .subscribe(object : Subscriber<LoginResponse>() {

                    override fun onCompleted() {
                    }

                    override fun onError(e: Throwable?) {
                        if (views != null){
                            views?.showHideProgress(false)
                            views?.showSnackBar("Something when wrong")
                            if (e?.cause is NetworkErrorException || e?.cause is SocketTimeoutException) {
                                views?.showSnackBar("No Internet Connection")
                            }
                        }
                    }

                    override fun onNext(t: LoginResponse?) {
                        if (views != null) {
                           views?.goToProfilePage(t)
                        }
                    }

                })
    }

}