package io.feelfree.estimotezabytki.base

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> {
    var view : T? = null
    val subscriptions = CompositeDisposable()

    open fun subscribe(view: T) {
        this.view = view
    }

    open fun unsubscribe() {
        view = null
        subscriptions.clear()
    }
}