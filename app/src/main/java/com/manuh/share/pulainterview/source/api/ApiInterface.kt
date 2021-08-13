package com.manuh.share.pulainterview.source.api

import com.manuh.share.pulainterview.model.Response
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("/v3/d628facc-ec18-431d-a8fc-9c096e00709a")
    fun getQuestions(): Observable<Response>?
}