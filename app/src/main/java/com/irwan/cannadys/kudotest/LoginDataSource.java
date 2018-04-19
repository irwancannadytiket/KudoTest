package com.irwan.cannadys.kudotest;

import com.irwan.cannadys.kudotest.model.LoginResponse;

import rx.Observable;

/**
 * Created by irwancannady on 4/17/18.
 */

public interface LoginDataSource {

    Observable<LoginResponse> getLogin(String userMail, String userPassword);
}
