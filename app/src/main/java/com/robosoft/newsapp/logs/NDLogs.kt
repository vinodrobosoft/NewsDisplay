package com.robosoft.newsapp.logs

import android.util.Log

object NDLogs {

    private const val TAG = "nd"

    internal var enableLogger: Boolean = true
        set(value) {
            field = value
        }

    fun debug(tag: String, message: String, enableLogger: Boolean = this.enableLogger) {

        if (enableLogger) Log.d(TAG,"$tag $message")
    }

    fun error(tag: String, message: String, error: Throwable, enableLogger: Boolean = this.enableLogger) {

        if (enableLogger) Log.e(TAG, "$tag $message")
    }

    fun info(tag: String, message: String, enableLogger: Boolean = this.enableLogger) {

        if (enableLogger) Log.i(TAG, "$tag $message")
    }

    fun trace(tag: String, message: String, enableLogger: Boolean = this.enableLogger) {

        if (enableLogger)  Log.v(TAG, "$tag $message")
    }

    fun warn(tag: String, message: String, enableLogger: Boolean = this.enableLogger) {

        if (enableLogger) Log.w(TAG, "$tag $message")
    }
}