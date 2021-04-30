package com.example.myapplication

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

class Utill {

    companion object {

        /**
         * Serialize an object to Json.
         *
         * @param object to serialize.
         */
        fun serialize(`object`: Any?, clazz: Class<*>?): String? {
            return Gson().toJson(`object`, clazz)
        }

        /**
         * Deserialize a json representation of an object
         *
         * @param string A json string to deserialize.
         */
        fun <T> deserialize(string: String?, clazz: Class<T>?): T {
            return Gson().fromJson(string, clazz)
        }

        /**
         * Read file from assets folder
         *
         * @param context  to get assets dir.
         * @param fileName name of assets dir. file
         * @param fileExt  file extension of assets dir. file
         */
        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}