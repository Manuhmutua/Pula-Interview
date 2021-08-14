package com.manuh.share.pulainterview.repository

import androidx.lifecycle.LiveData
import com.manuh.share.pulainterview.dao.*
import com.manuh.share.pulainterview.model.*
import com.manuh.share.pulainterview.source.api.ApiInterface
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AppRepository @Inject constructor(
    var answerDao: AnswerDao?,
    var optionsDao: OptionsDao?,
    var enDao: EnDao?,
    var questionDao: QuestionDao?,
    var responseDao: ResponseDao?,
    var apiInterface: ApiInterface?
) {

    open class ItemViewModel(
        var answerDao: AnswerDao,
        var optionsDao: OptionsDao,
        var enDao: EnDao,
        var questionDao: QuestionDao,
        var apiInterface: ApiInterface,
        var responseDao: ResponseDao
    )

    fun apiQuestions(): Observable<Response>? =
        apiInterface?.getQuestions()

    fun insertAnswer(answer: Answer?) {
        answerDao!!.insertAnswer(answer!!)
    }

    fun insertEn(en: En) {
        enDao!!.insertEn(en)
    }

    fun insertOption(option: Option) {
        optionsDao!!.insertOption(option)
    }

    fun insertResponse(response: Response) {
        responseDao!!.insertResponse(response)
    }

    fun insertQuestion(question: Question) {
        questionDao!!.insertQuestion(question)
    }

    fun getQuestion(id: String): Question? = questionDao!!.getQuestion(id)

    fun getEn(): En? = enDao!!.getEn()

    fun getOptions(id: String):List<Option?>? = optionsDao!!.getOptions(id)

    fun getResponse(): LiveData<Response?>? = responseDao!!.getResponse()

    fun getQuestions(): List<Question?>? = questionDao!!.getQuestions()
}