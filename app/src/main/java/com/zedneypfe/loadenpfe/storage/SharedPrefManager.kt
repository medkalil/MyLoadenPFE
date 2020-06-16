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


    var isLoggedIn: Boolean
        //linking the id from this var(isLoggedIn) with id from user(PHONE)
        //and initialize this var to false with : ...null != null (line 14)
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("phone", null) != null
        }
        set(value) {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("result", value.toString())
            editor.apply()
        }

    val user: authModel
        //custom getter too getting user
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return authModel(
                sharedPreferences.getString("result", null).toString(),
                sharedPreferences.getString("user_type", null).toString(),
                sharedPreferences.getString("verif_code", null).toString()
            )
        }
    val phone: String
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("phone", null).toString()

        }


    fun saveUser(user: authModel) {

        val sharedPreferences = mCtx.getSharedPreferences(
            Companion.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("result", user.result)
        editor.putString("user_type", user.user_type)
        editor.putString("verif_code", user.verif_code)
        editor.apply()
    }

    fun save_phone(phone: String) {

        val sharedPreferences = mCtx.getSharedPreferences(
            Companion.SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putString("phone", phone)
        println("PHONE Saved "+phone)
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