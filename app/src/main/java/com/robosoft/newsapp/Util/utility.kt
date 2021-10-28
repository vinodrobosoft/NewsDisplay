package com.robosoft.newsapp.Util

object utility {

    fun splitSourceString(source:String) : String {

        val source2 = source.split("=").toTypedArray()
        val source3 = source2.get(2).removeSuffix("}")
        return source3
    }
}