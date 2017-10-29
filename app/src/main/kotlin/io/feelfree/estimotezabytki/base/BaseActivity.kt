package io.feelfree.estimotezabytki.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.feelfree.estimotezabytki.ui.dialogs.createAlertBuilder

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun showErrorDialog(e: Throwable) {
        createAlertBuilder().setTitle("Error").setMessage(e.localizedMessage).show()
    }
}