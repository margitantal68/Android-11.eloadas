package ro.sapientia.android_11eloadas

import android.app.Application

// Temporary solution
// Store the token in SharedPreferences
class MyApplication : Application() {
    companion object {
        var token: String = ""
        var email: String = ""
        var password: String = ""
    }
}