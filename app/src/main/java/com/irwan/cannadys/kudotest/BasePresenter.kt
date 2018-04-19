package com.irwan.cannadys.kudotest

/**
 * Created by irwancannady on 4/17/18.
 */
interface BasePresenter<V> {


    abstract fun bind(view: V)
    abstract fun unbind()
}