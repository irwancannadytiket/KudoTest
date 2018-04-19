package com.irwan.cannadys.kudotest.profile

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.irwan.cannadys.kudotest.MainActivity
import com.irwan.cannadys.kudotest.R
import com.irwan.cannadys.kudotest.commons.SharedManager
import com.irwan.cannadys.kudotest.databinding.ActivityMainProfileBinding
import com.squareup.picasso.Picasso

/**
 * Created by irwancannady on 4/17/18.
 */
class MainProfile : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainProfileBinding
    private lateinit var sharedManager: SharedManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_profile)

        sharedManager = SharedManager(this)
        initView()
    }

    private fun initView() {
        mBinding.userMail.text = sharedManager.getSharedEmail()
        mBinding.firstName.text = sharedManager.getSharedfirstName()
        mBinding.lastName.text = sharedManager.getSharedLastName()
        mBinding.joinDate.text = sharedManager.getSharedJoinDate()
        mBinding.desc.text = sharedManager.getSharedDesc()
        Picasso.with(this).load(sharedManager.getAvatar())
                .placeholder(R.drawable.userplaceholder).into(mBinding.ivAvatar)

        mBinding.btnLogout.setOnClickListener {
            logout()
        }
    }

    companion object {
        const val VALUE_LOGIN = "value_login"
        @JvmStatic
        fun startToProfile(activity: Activity) {
            val intent = Intent(activity, MainProfile::class.java)
            intent.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            activity.startActivity(intent)
            activity.finish()
        }
    }

    private fun logout(){
        sharedManager.saveSharedBoolean(SharedManager.SP_ALREADY_LOGIN, false)
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        finish()

    }
}