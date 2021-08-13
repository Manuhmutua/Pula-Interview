package com.manuh.share.pulainterview.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.manuh.share.pulainterview.model.Question
import com.manuh.share.pulainterview.model.Response
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
                    item.strings?.let { repository!!.insertEn(it) }
                    item.questions?.forEach { question ->
                        repository!!.insertQuestion(question)
                        question.options?.forEach { option ->
                            repository!!.insertOption(option)
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

    fun getQuestion(id: String): LiveData<Question?>? = repository!!.getQuestion(id)

    companion object {
        private const val TAG = "ItemViewModel"
    }

}