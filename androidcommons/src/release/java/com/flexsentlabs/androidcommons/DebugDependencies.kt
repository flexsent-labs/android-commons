package com.flexsentlabs.androidcommons

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object DebugDependencies {

    fun addOkHttpInterceptors(builder: OkHttpClient.Builder): OkHttpClient.Builder = builder

    fun addOkHttpInterceptors(okHttpClient: com.squareup.okhttp.OkHttpClient): com.squareup.okhttp.OkHttpClient =
        okHttpClient
}