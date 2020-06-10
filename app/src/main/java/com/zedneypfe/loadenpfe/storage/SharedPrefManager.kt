package com.zedneypfe.loadenpfe.storage

import android.content.Context
import com.zedneypfe.loadenpfe.Model.PHONE

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        //linking the id from this var(isLoggedIn) with id from user(PHONE)
        //and initialize this var to false with : ...null != null (line 14)
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("id", null) != null
        }

    val user: PHONE
        //custom getter too getting user
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return PHONE(
                sharedPreferences.getString("id", null).toString(),
                sharedPreferences.getString("type_id", null).toString(),
                sharedPreferences.getString("value", null).toString(),
                sharedPreferences.getString("value_type", null).toString()
            )
        }


    fun saveUser(user: PHONE) {

        val sharedPreferences = mCtx.getSharedPreferences(
            Companion.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("id", user.ID)
        editor.putString("type_id", user.TYPE_ID)
        editor.putString("value", user.VALUE)
        editor.putString("value_type", user.VALUE_TYPE)
        editor.apply()
    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}
