package com.irwan.cannadys.kudotest.commons;

import android.support.annotation.NonNull;

import rx.Scheduler;

/**
 * Created by irwancannady on 8/27/17.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
