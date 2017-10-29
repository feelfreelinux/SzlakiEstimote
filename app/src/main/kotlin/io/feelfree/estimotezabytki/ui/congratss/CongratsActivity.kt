package io.feelfree.estimotezabytki.ui.congratss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import kotlinx.android.synthetic.main.activity_trail_result.*

fun Context.startCongratsActivity(score : Int) {
    val intent = Intent(this, CongratsActivity::class.java)
    intent.putExtra(CongratsActivity.EXTRA_RESULT, score)
    startActivity(intent)
}

class CongratsActivity : BaseActivity() {
    companion object {
        val EXTRA_RESULT = "EXTRA_RESULT"
    }

    val score by lazy { intent.getIntExtra(EXTRA_RESULT, -1) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trail_result)
        result.text = score.toString()
        supportActionBar?.title = "Wynik"
        shareResult.setOnClickListener {
            val shareBody = "Zdobyłem $score punktów w Szlaki Estimote!"
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Wynik")
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Udostępnij poprzez"))
        }
    }
}