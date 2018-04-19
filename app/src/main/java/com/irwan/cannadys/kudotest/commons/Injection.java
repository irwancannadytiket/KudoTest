package com.irwan.cannadys.kudotest.commons;

import android.content.Context;
import android.support.annotation.NonNull;

import com.irwan.cannadys.kudotest.service.RemoteDataSource;

/**
 * Created by irwancannady on 8/27/17.
 */

public class Injection {

    public static CoreAppRepository provideCoreAppRepository(@NonNull Context context){
        return CoreAppRepository.getInstance(RemoteDataSource.getInstance(context.getApplicationContext()));
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
