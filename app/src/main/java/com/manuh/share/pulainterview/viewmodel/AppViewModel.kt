package com.manuh.share.pulainterview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manuh.share.pulainterview.model.*
import com.manuh.share.pulainterview.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(var repository: AppRepository?) :
    ViewModel() {

    fun getItem() {
        repository!!.apiQuestions()
            ?.subscribeOn(Schedulers.io())
            ?.map { itemResponse ->
                val item: Response? = itemResponse
                if (item != null) {
                    repository!!.insertResponse(item)
                    item.strings?.en.let {
                        repository!!.insertEn(it!!)
                        Log.i("Question:", it.q_farmer_gender)
                    }
                    item.questions?.forEach { question ->
                        repository!!.insertQuestion(question)
                        question.options?.forEach { option ->
                            Log.i("OPTION:", option.display_text)
                            val o = Option(question.id, option.value, option.display_text)
                            repository!!.insertOption(o)
                        }
                    }
                }
                item
            }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ _ ->
            },
                { error -> Log.e(TAG, "getItem: " + error.message) })
    }

    fun getQuestion(id: String): Question? = repository!!.getQuestion(id)

    fun getEn(): En? = repository!!.getEn()

    fun getOptions(id: String): List<Option?>? = repository!!.getOptions(id)

    fun getResponse(): LiveData<Response?>? = repository!!.getResponse()

    fun getQuestions(): List<Question?>? = repository!!.getQuestions()

    fun insertAnswer(answer: Answer) = repository!!.insertAnswer(answer)

    companion object {
        private const val TAG = "ItemViewModel"
    }

}