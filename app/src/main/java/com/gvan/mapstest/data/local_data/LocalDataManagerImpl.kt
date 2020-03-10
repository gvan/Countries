package com.gvan.mapstest.data.local_data

import android.content.Context
import com.gvan.mapstest.R

class LocalDataManagerImpl constructor(private val context: Context) : LocalDataManager {

    override fun getRapidKey(): String {
        return context.getString(R.string.rapidapi_key)
    }
}