package com.sample.base

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AuthenticationInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(HEADER_NAME, "Client-ID ${BuildConfig.CLIENT_ID}")
        val request = builder.build()
        Timber.d("AuthenticationInterceptor:${request}")
        return chain.proceed(request)
    }

    private companion object {
        private const val HEADER_NAME = "Authorization"
    }
}