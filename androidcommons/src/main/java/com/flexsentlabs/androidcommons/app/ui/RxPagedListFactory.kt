package com.flexsentlabs.androidcommons.app.ui

import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxPagedListFactory {

    fun <TId, TModel> create(
        ordersPagedDataSourceFactory: DataSource.Factory<TId, TModel>,
        config: PagedList.Config
    ): Flowable<PagedList<TModel>> =
        RxPagedListBuilder<TId, TModel>(ordersPagedDataSourceFactory, config)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            // get only the latest paged list if we have backpressure
            .buildFlowable(BackpressureStrategy.LATEST)
}