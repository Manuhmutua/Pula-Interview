package com.manuh.share.pulainterview.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        // Your work here.
        // Your task result
        uploadData()
        return Result.success()
    }

    private fun uploadData() {
        // do upload work here
    }
}