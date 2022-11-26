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

        // Read data from preferences
        val prefs = this.getPreferences(MODE_PRIVATE)
        val token = prefs.getString("token", "")
        val deadline = prefs.getString("deadline", "")

        Log.i("xxx", "token: " + token)
        // @TODO - check the token's validity
        val isValid = true
        if (!token.equals("") && isValid ) {
            MyApplication.token = token!!
            MyApplication.email = prefs.getString("email","")!!

            controller.navigate(R.id.listFragment)
        }
    }

}