package com.zedneypfe.loadenpfe.utils

import android.util.Log
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

/**
 * Created by Zedney Creative on 14/02/2019.
 */

class ExceptionHandler : Thread.UncaughtExceptionHandler {
//    private val LINE_SEPARATOR = "\n"

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        val stackTrace = StringWriter()
        val stack = PrintWriter(stackTrace)
        exception.printStackTrace(stack)

        val errorReport = StringBuilder()
        errorReport.append(stackTrace.toString())

        Log.e(LOG_TAG, errorReport.toString())
        //   FirebaseCrash.report(exception)

        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(10)


    }

    companion object {
        val LOG_TAG = ExceptionHandler::class.java.simpleName
    }
}

