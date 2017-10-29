package io.feelfree.estimotezabytki.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ExecutionSchedulers {
    fun observeScheduler() : Scheduler
    fun subscribeScheduler() : Scheduler
}

class ApplicationSchedulers : ExecutionSchedulers {
    override fun observeScheduler(): Scheduler = AndroidSchedulers.mainThread()

    override fun subscribeScheduler(): Scheduler = Schedulers.io()
}