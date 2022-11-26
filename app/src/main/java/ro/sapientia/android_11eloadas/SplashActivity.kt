package ro.sapientia.android_11eloadas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ro.sapientia.android_11eloadas.util.Constants

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefs = this.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString("token", "")
        Log.i("xxx-Splash", token!!)
        MyApplication.token = token!!
        startActivity(Intent(this, MainActivity::class.java) )
    }
}