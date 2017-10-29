package io.feelfree.estimotezabytki.base

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.feelfree.estimotezabytki.ui.dialogs.createAlertBuilder

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun showErrorDialog(e: Throwable) {
        createAlertBuilder().setTitle("Error").setMessage(e.localizedMessage).show()
    }
}