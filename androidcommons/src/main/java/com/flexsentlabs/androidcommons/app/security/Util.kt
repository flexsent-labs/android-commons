package com.flexsentlabs.androidcommons.app.security

import kotlin.experimental.and

object Util {

    private val hexArray = "0123456789ABCDEF".toCharArray()

    //Original source: https://stackoverflow.com/a/9855338/1389357
    fun bytesToHex(bytes: ByteArray): String {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = (bytes[j] and 0xFF.toByte()).toInt()

            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}