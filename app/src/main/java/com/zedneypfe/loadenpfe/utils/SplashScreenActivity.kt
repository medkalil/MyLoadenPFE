package com.zedneypfe.loadenpfe.utils

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zedneypfe.loadenpfe.MainActivity
import com.zedneypfe.loadenpfe.R
import java.lang.Boolean


class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler :Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        handler = Handler()
        handler.postDelayed({
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)

    }//onCreate



}