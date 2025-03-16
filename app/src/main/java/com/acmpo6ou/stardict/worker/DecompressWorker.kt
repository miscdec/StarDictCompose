package com.acmpo6ou.stardict.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.acmpo6ou.stardict.utils.app.utils.NotificationUtils
import com.acmpo6ou.stardict.utils.app.utils.NotificationUtils.CHANNEL_ID_DOWNLOAD
import splitties.init.appCtx

class DecompressWorker(context: Context, workerParams: WorkerParameters) :Worker(context, workerParams) {
    /**
     * Override this method to do your actual background processing.  This method is called on a
     * background thread - you are required to **synchronously** do your work and return the
     * [androidx.work.ListenableWorker.Result] from this method.  Once you return from this
     * method, the Worker is considered to have finished what its doing and will be destroyed.  If
     * you need to do your work asynchronously on a thread of your own choice, see
     * [ListenableWorker].
     *
     *
     * A Worker has a well defined
     * [execution window](https://d.android.com/reference/android/app/job/JobScheduler)
     * to finish its execution and return a [androidx.work.ListenableWorker.Result].  After
     * this time has expired, the Worker will be signalled to stop.
     *
     * @return The [androidx.work.ListenableWorker.Result] of the computation; note that
     * dependent work will not execute if you use
     * [androidx.work.ListenableWorker.Result.failure] or
     * [androidx.work.ListenableWorker.Result.failure]
     */
    override fun doWork(): Result {
       NotificationUtils.notify(1,NotificationCompat.Builder(appCtx, CHANNEL_ID_DOWNLOAD).build())


        return Result.success()
    }
}