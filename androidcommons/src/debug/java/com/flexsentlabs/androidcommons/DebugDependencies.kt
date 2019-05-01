package com.flexsentlabs.androidcommons

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object DebugDependencies {

    fun addOkHttpInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        builder
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(httpLoggingInterceptor)
        return builder
    }

    fun addOkHttpInterceptors(okHttpClient: com.squareup.okhttp.OkHttpClient): com.squareup.okhttp.OkHttpClient {
        val httpLoggingInterceptor = com.squareup.okhttp.logging.HttpLoggingInterceptor()

        httpLoggingInterceptor.level = com.squareup.okhttp.logging.HttpLoggingInterceptor.Level.BODY

        @Suppress("DEPRECATION")
        okHttpClient.networkInterceptors().add(com.facebook.stetho.okhttp.StethoInterceptor())
        okHttpClient.networkInterceptors().add(httpLoggingInterceptor)
        return okHttpClient
    }
}