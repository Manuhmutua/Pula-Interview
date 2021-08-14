package com.manuh.share.pulainterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.manuh.share.pulainterview.model.En
import com.manuh.share.pulainterview.model.Option
import com.manuh.share.pulainterview.model.Question
import com.manuh.share.pulainterview.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: AppViewModel
    lateinit var question: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question = findViewById(R.id.question)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        navController.navigate(R.id.citiesFragment)

        var

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]
        viewModel.getItem()
        viewModel.getResponse()?.observe(this) { item ->
            question.text = item?.start_question_id?.getString()
            Toast.makeText(
                this,
                item?.start_question_id?.let { getOptionsCount(it) }.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

//        viewModel.getOptions("q_farmer_gender")?.observe(this) { item ->
//            Toast.makeText(this, item?.size.toString(), Toast.LENGTH_LONG).show()
//        }

//        Toast.makeText(this, "q_farmer_name".getString(), Toast.LENGTH_LONG).show()

    }

    private fun nextQuestion(id: String): String {
        val question: Question? = viewModel.getQuestion(id)
        return question?.next!!.getString()
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