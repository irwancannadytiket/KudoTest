package com.irwan.cannadys.kudotest.commons

import android.R.id.edit
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


/**
 * Created by irwancannady on 4/18/18.
 */
class SharedManager(context: Context) {


    companion object {
        val SHARED_KUDO_TEST = "kudo_test"
        //    val SP_NAMA = "spNama"
        val SP_EMAIL = "spEmail"
        val SP_FIRST_NAME = "spFirstName"
        val SP_LAST_NAME = "spLastName"
        val SP_JOIN_DATE = "spJoinDate"
        val SP_AVATAR = "spAvatar"
        val SP_DESC = "spDesc"
        val SP_ALREADY_LOGIN = "spSudahLogin"
    }


    var shared: SharedPreferences
    var spEditor: SharedPreferences.Editor

    init {
        shared = context.getSharedPreferences(SHARED_KUDO_TEST, Context.MODE_PRIVATE)
        spEditor = shared.edit()
    }
//    fun SharedManager(context: Context){
//
//    }

    fun saveSPString(keySP: String, value: String) {
        spEditor.putString(keySP, value)
        spEditor.commit()
    }

    fun saveSharedInt(keySP: String, value: Int) {
        spEditor.putInt(keySP, value)
        spEditor.commit()
    }

    fun saveSharedBoolean(keySP: String, value: Boolean) {
        spEditor.putBoolean(keySP, value)
        spEditor.commit()
    }

//    fun getSPNama(): String {
//        return sp.getString(SP_NAMA, "")
//    }

    fun getSharedEmail(): String {
        return shared.getString(SP_EMAIL, "")
    }

    fun getSharedfirstName(): String {
        return shared.getString(SP_FIRST_NAME, "")
    }

    fun getSharedLastName(): String {
        return shared.getString(SP_LAST_NAME, "")
    }

    fun getSharedJoinDate(): String {
        return shared.getString(SP_JOIN_DATE, "")
    }

    fun getAvatar(): String {
        return shared.getString(SP_AVATAR, "")
    }

    fun getSharedDesc(): String {
        return shared.getString(SP_DESC, "")
    }

    fun getSharedAlreadyLogin(): Boolean {
        return shared.getBoolean(SP_ALREADY_LOGIN, false)
    }
}