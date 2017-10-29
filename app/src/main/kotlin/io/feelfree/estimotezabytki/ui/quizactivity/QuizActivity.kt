package io.feelfree.estimotezabytki.ui.quizactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import io.feelfree.estimotezabytki.R
import io.feelfree.estimotezabytki.base.BaseActivity
import io.feelfree.estimotezabytki.models.pojo.BeaconResponse
import io.feelfree.estimotezabytki.models.pojo.QuestionResponse
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : BaseActivity() {
    companion object {
        val BEACON_EXTRA = "BEACON"
        val EXTRA_CORRECT_ANSWERS = "EXTRA_CORRECT_ANSWERS"
        val RESPONSE_CODE = 221
    }

    val beacon by lazy { intent.getParcelableExtra<BeaconResponse>(BEACON_EXTRA) }

    var correctAnswers = 0
    var questionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Quiz"
        setContentView(R.layout.activity_quiz)
        askQuestion(beacon.questions!![questionIndex])
    }

    fun askQuestion(questionResponse: QuestionResponse) {
        nextQuestion.visibility = View.GONE
        pytanie.text = questionResponse.question

        for (i in 0 until questionResponse.answers.size) {
            getButtonForIndex(i).apply {
                text = questionResponse.answers[i]
                setOnClickListener {
                    nextQuestion.visibility = View.VISIBLE
                    nextQuestion.setOnClickListener {
                        for (d in 0 .. 2) {
                            getButtonForIndex(d).setTextColor(Color.BLACK)
                            getButtonForIndex(d).isClickable = true
                        }

                        if (questionIndex + 1 < beacon.questions!!.size) {
                            questionIndex++
                            askQuestion(beacon.questions!![questionIndex])
                        } else {
                            val bundle = Intent()
                            bundle.putExtra(EXTRA_CORRECT_ANSWERS, correctAnswers)
                            setResult(Activity.RESULT_OK, bundle)
                            finish()
                        }
                    }

                    for (d in 0 .. 2) getButtonForIndex(d).isClickable = false
                    if (questionResponse.rightAnswerIndex == i) {
                        correctAnswer(i)
                    } else incorrectAnswer(i)
                }
            }
        }
    }

    fun incorrectAnswer(index : Int) {
        getButtonForIndex(index).setTextColor(Color.RED)
    }

    fun correctAnswer(index : Int) {
        correctAnswers++
        getButtonForIndex(index).setTextColor(Color.GREEN)
    }

    fun getButtonForIndex(index : Int) : Button {
        return when(index) {
            0 -> odpA
            1 -> odpB
            else -> odpC
        }
    }
}