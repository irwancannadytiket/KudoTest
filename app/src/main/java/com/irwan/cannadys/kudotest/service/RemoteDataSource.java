package com.irwan.cannadys.kudotest.service;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.annotation.NonNull;

import com.irwan.cannadys.kudotest.LoginDataSource;
import com.irwan.cannadys.kudotest.commons.ConnectionStatus;
import com.irwan.cannadys.kudotest.model.LoginResponse;

import rx.Observable;

/**
 * Created by irwancannady on 8/27/17.
 */

public class RemoteDataSource implements LoginDataSource {

    private LoginService mServiceApi;
    private Context mContext;
    private static RemoteDataSource sInstance = null;

    public static RemoteDataSource getInstance(@NonNull Context context){
        if (sInstance == null){
            sInstance = new RemoteDataSource(context.getApplicationContext());
        }
        return sInstance;
    }

    private RemoteDataSource(Context context){
        mContext = context;
        mServiceApi = RetrofitHelper.getInstance(context).provideRetrofit().create(LoginService.class);
    }

    @Override
    public Observable<LoginResponse> getLogin(String userMail, String userPassword) {
        if (!ConnectionStatus.hasNetwork(mContext)){
            return Observable.error(new Throwable(new NetworkErrorException()));
        }
        return mServiceApi.getLogin(userMail, userPassword);
    }
}
