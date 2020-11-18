package com.plant.utils

import android.content.Context
import java.io.IOException



class Utils {
    companion object {
        fun getJsonData(context: Context, fileName : String): String? {
            val json: String
            try {
                val inputStream = context.assets.open(fileName)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.use { it.read(buffer) }

                json = String(buffer)
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return json
        }
    }

}