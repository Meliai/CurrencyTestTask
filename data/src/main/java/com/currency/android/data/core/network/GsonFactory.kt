package com.currency.android.data.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Factory for creating [Gson] instances.
 */
object GsonFactory {

    fun create(): Gson = GsonBuilder()
        .setLenient()
        .create()
}