package com.irwan.cannadys.kudotest

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.irwan.cannadys.kudotest.commons.ConnectionStatus
import com.irwan.cannadys.kudotest.commons.Injection
import com.irwan.cannadys.kudotest.commons.SharedManager
import com.irwan.cannadys.kudotest.databinding.ActivityMainBinding
import com.irwan.cannadys.kudotest.model.LoginResponse
import com.irwan.cannadys.kudotest.profile.MainProfile

class MainActivity : AppCompatActivity() , MainActivityContract.View, View.OnClickListener{

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var presenter: MainActivityContract.Presenter
    private var loginResponse: LoginResponse ? = null

    private lateinit var sharedManager: SharedManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        sharedManager = SharedManager(this)

        if (!ConnectionStatus.hasNetwork(this)){
            showSnackBar("No Internet Connection !!")
        }

        if (sharedManager.getSharedAlreadyLogin()){
            startActivity(Intent(this, MainProfile::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            finish()
        }

        presenter = initPresenter()
        presenter.bind(this)
        mBinding.btnLogin.setOnClickListener(this)
    }

    override fun initPresenter(): MainActivityContract.Presenter {
        return MainActivityPresenter(Injection.provideCoreAppRepository(applicationContext),
                Injection.provideSchedulerProvider())
    }

    private fun initSignIn(){
        val email = mBinding.etMail.text.toString()
        val password = mBinding.etPassword.text.toString()
        if (email == "" || password == ""){
            toast("Please Input your email or password")
        }else {
            presenter.getLogin(email, password)

        }
    }

    override fun showSnackBar(message: String){
        val snackbar = Snackbar.make(mBinding.coordinator, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_login -> initSignIn()
        }
    }

    override fun showHideProgress(status: Boolean) {
        when(status){
            true -> showProgress()
            else -> hideProgress()
        }
    }

    override fun showProgress() {
        mBinding.rlLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mBinding.rlLoading.visibility = View.GONE
    }

    override fun goToProfilePage(loginResponse: LoginResponse?) {
        loginResponse?.message?.apply {
            sharedManager.saveSPString(SharedManager.SP_EMAIL, loginResponse.message.userMail)
            sharedManager.saveSPString(SharedManager.SP_FIRST_NAME, loginResponse.message.first_name)
            sharedManager.saveSPString(SharedManager.SP_LAST_NAME, loginResponse.message.lastName)
            sharedManager.saveSPString(SharedManager.SP_JOIN_DATE, loginResponse.message.joinDate)
            sharedManager.saveSPString(SharedManager.SP_DESC, loginResponse.message.description)
            sharedManager.saveSPString(SharedManager.SP_AVATAR, loginResponse.message.avatar)

            sharedManager.saveSharedBoolean(SharedManager.SP_ALREADY_LOGIN, true)
        }
        MainProfile.startToProfile(this)
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
