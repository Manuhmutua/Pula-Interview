package com.manuh.share.pulainterview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.manuh.share.pulainterview.adapter.OptionsAdapter
import com.manuh.share.pulainterview.datastore.OptionManager
import com.manuh.share.pulainterview.model.Answer
import com.manuh.share.pulainterview.model.En
import com.manuh.share.pulainterview.model.Option
import com.manuh.share.pulainterview.viewmodel.AppViewModel
import com.manuh.share.pulainterview.worker.UploadWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    lateinit var viewModel: AppViewModel
    var question: TextView? = null
    var editTextAnswer: EditText? = null
    var options: List<Option?>? = null
    var mAdapter: OptionsAdapter? = null
    private lateinit var rvOptions: RecyclerView
    lateinit var navigate: Button
    var currentQuestion: String? = ""
    var count: Int? = 0
    var optionManager: OptionManager? = null
    var formUUIDString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uuid: UUID = UUID.randomUUID()
        formUUIDString = uuid.toString()

        optionManager = OptionManager(this)

        launch {
            optionManager!!.storeIndex(0, applicationContext)
        }

        question = findViewById(R.id.textViewQuestion)
        rvOptions = findViewById(R.id.rvOptions)
        navigate = findViewById(R.id.buttonNavigate)
        editTextAnswer = findViewById(R.id.editTextAnswer)


        rvOptions.adapter = mAdapter
        rvOptions.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[AppViewModel::class.java]
        viewModel.getItem()
        viewModel.getResponse()?.observe(this) { item ->
            if (item != null)
                currentQuestion = item.start_question_id
            question?.text = currentQuestion!!.getString()

            editTextAnswer?.visibility = View.VISIBLE
            rvOptions.visibility = View.GONE

            if (item != null)
                if (getOptionsCount(item.start_question_id) > 0) {
                    question?.text = currentQuestion!!.getString()

                    editTextAnswer?.visibility = View.GONE
                    rvOptions.visibility = View.VISIBLE

                    options = getOptions(item.start_question_id)

                    mAdapter = OptionsAdapter(options!!.toMutableList(), this)
                    rvOptions.adapter = mAdapter
                    mAdapter?.updateData(options!!.toMutableList())
                }
        }

        navigate.text = "Next"
        navigate.setOnClickListener {
            val question = viewModel.getQuestion(currentQuestion!!)

            val questions = viewModel.getQuestions()

            if (count!! == questions?.size!!) {
                clickNext("")
                recreate()
            } else {
                clickNext(question!!.next)
            }
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val work = PeriodicWorkRequestBuilder<UploadWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork("", ExistingPeriodicWorkPolicy.KEEP, work)

    }

    @SuppressLint("SetTextI18n")
    private fun clickNext(id: String) {

        count = count!! + 1

        var genderOptionChoice = ""

        val a = Answer()

        if (id != "") {

            currentQuestion = id

            question?.text = currentQuestion!!.getString()

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
                question?.text = currentQuestion!!.getString()

                editTextAnswer?.visibility = View.GONE
                rvOptions.visibility = View.VISIBLE

                options = getOptions(id)

                mAdapter = OptionsAdapter(options!!.toMutableList(), this)
                rvOptions.adapter = mAdapter
                mAdapter?.updateData(options!!.toMutableList())

                optionManager?.indexFlow?.asLiveData()?.observe(this) {
                    genderOptionChoice = getOptions(id)?.get(it)?.display_text.toString()
                    a.id = formUUIDString.toString()
                    a.farmer_gender = genderOptionChoice
                    viewModel.updateAnswer(a)
                }
            }
        }

        val answer: String = editTextAnswer?.text.toString()

        if (answer != "") {
            if (count == 1) {
                a.id = formUUIDString.toString()
                a.farmer_name = answer
                viewModel.insertAnswer(a)
            } else if (count == 3) {
                viewModel.updateLand(answer, formUUIDString.toString())
            }
        }

        editTextAnswer?.setText("")
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
