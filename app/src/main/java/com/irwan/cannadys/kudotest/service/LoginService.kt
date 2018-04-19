package com.irwan.cannadys.kudotest.service

import com.irwan.cannadys.kudotest.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*
import rx.Observable

/**
 * Created by irwancannady on 4/17/18.
 */
interface LoginService {

    @FormUrlEncoded
    @POST("authentication/login")
    fun getLogin(@Field("user_mail") user_main: String,
                 @Field("user_password") password: String) : Observable<LoginResponse>
}