package com.manuh.share.pulainterview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manuh.share.pulainterview.adapter.OptionsAdapter
import com.manuh.share.pulainterview.model.En
import com.manuh.share.pulainterview.model.Option
import com.manuh.share.pulainterview.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel
    var question: TextView? = null
    var editTextAnswer: EditText? = null
    var options: List<Option?>? = null
    var mAdapter: OptionsAdapter? = null
    private lateinit var rvOptions: RecyclerView
    lateinit var navigate: Button
    lateinit var currentQuestion: String
    var count: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question = findViewById(R.id.textViewQuestion)
        rvOptions = findViewById(R.id.rvOptions)
        navigate = findViewById(R.id.buttonNavigate)
        editTextAnswer = findViewById(R.id.editTextAnswer)


        rvOptions.adapter = mAdapter
        rvOptions.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]
        viewModel.getItem()
        viewModel.getResponse()?.observe(this) { item ->
            currentQuestion = item!!.start_question_id
            question?.text = currentQuestion.getString()

            editTextAnswer?.visibility = View.VISIBLE
            rvOptions.visibility = View.GONE

            if (getOptionsCount(item.start_question_id) > 0) {
                question?.text = currentQuestion.getString()

                editTextAnswer?.visibility = View.GONE
                rvOptions.visibility = View.VISIBLE

                options = getOptions(item.start_question_id)

                mAdapter = OptionsAdapter(options!!.toMutableList())
                rvOptions.adapter = mAdapter
                mAdapter?.updateData(options!!.toMutableList())
            }
        }

        navigate.text = "Next"
        navigate.setOnClickListener {
            val question = viewModel.getQuestion(currentQuestion)

            val questions = viewModel.getQuestions()

            if (count!! == questions?.size!!) {
                count = 0
                recreate()
            } else {
                clickNext(question!!.next)
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun clickNext(id: String) {
        count = count!! + 1

        currentQuestion = id

        question?.text = currentQuestion.getString()

        val questions = viewModel.getQuestions()

        when (count) {
            questions?.size -> {
                navigate.text = "Finish"
            }
            else -> {
                navigate.text = "Next"
            }
        }
        editTextAnswer?.visibility = View.VISIBLE
        rvOptions.visibility = View.GONE

        if (getOptionsCount(id) > 0) {
            question?.text = currentQuestion.getString()

            editTextAnswer?.visibility = View.GONE
            rvOptions.visibility = View.VISIBLE

            options = getOptions(id)

            mAdapter = OptionsAdapter(options!!.toMutableList())
            rvOptions.adapter = mAdapter
            mAdapter?.updateData(options!!.toMutableList())
        }

    }


    private fun getOptions(id: String): List<Option?>? {
        return viewModel.getOptions(id)
    }

    private fun getOptionsCount(id: String): Int {
        return viewModel.getOptions(id)!!.size
    }

    private fun String.getString(): String {
        val en: En? = viewModel.getEn()
        when (this) {
            "q_farmer_name" -> {
                return en!!.q_farmer_name
            }
            "q_farmer_gender" -> {
                return en!!.q_farmer_gender
            }
            "opt_male" -> {
                return en!!.opt_male
            }
            "opt_female" -> {
                return en!!.opt_female
            }
            "opt_other" -> {
                return en!!.opt_other
            }
            "q_size_of_farm" -> {
                return en!!.q_size_of_farm
            }
            else -> return "Invalid String"
        }
    }
}