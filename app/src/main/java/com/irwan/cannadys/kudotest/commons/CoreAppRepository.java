package com.irwan.cannadys.kudotest.commons;

import android.support.annotation.NonNull;

import com.irwan.cannadys.kudotest.LoginDataSource;
import com.irwan.cannadys.kudotest.service.RemoteDataSource;
import com.irwan.cannadys.kudotest.model.LoginResponse;

import rx.Observable;

/**
 * Created by irwancannady on 8/27/17.
 */

public class CoreAppRepository implements LoginDataSource {

    private static CoreAppRepository sInstance = null;

    private final RemoteDataSource mRemoteDataSource;

    public CoreAppRepository(@NonNull RemoteDataSource mRemoteDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static CoreAppRepository getInstance(@NonNull RemoteDataSource remoteDataSource){
        if (sInstance == null){
            sInstance = new CoreAppRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Observable<LoginResponse> getLogin(String userMail, String userPassword) {
        return mRemoteDataSource.getLogin(userMail, userPassword);
    }
}
