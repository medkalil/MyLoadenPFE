package com.zedneypfe.loadenpfe.storage

import android.content.Context
import com.zedneypfe.loadenpfe.Model.authModel

class SharedPrefManager private constructor(private val mCtx: Context) {

    /*
    *
    "result": "ok",
    "verif_code": "8615",
    "user_type": "1"
    *
    * */


    val isLoggedIn: Boolean
        //linking the id from this var(isLoggedIn) with id from user(PHONE)
        //and initialize this var to false with : ...null != null (line 14)
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("result", null) != null
        }

    val user: authModel
        //custom getter too getting user
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return authModel(
                sharedPreferences.getString("result", null).toString(),
                sharedPreferences.getString("verif_code", null).toString(),
                sharedPreferences.getString("user_type", null).toString()
            )
        }


    fun saveUser(user: authModel) {

        val sharedPreferences = mCtx.getSharedPreferences(
            Companion.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("id", user.result)
        editor.putString("type_id", user.verif_code)
        editor.putString("value", user.user_type)
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
