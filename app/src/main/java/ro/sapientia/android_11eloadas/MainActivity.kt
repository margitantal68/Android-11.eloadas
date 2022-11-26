package ro.sapientia.android_11eloadas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import ro.sapientia.android_11eloadas.util.Constants

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val controller = findNavController(R.id.nav_host_fragment)

        val prefs = this.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
        val token = prefs.getString("token", "")
        Log.i("xxx", "token: " + token)
        if( !token.equals("")) {
            MyApplication.token = token!!
            controller.navigate(R.id.listFragment)
        }


    }


}