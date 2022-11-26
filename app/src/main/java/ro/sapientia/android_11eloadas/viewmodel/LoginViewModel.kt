package ro.sapientia.android_11eloadas.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.sapientia.android_11eloadas.MyApplication
import ro.sapientia.android_11eloadas.model.LoginRequest
import ro.sapientia.android_11eloadas.repository.TrackerRepository
import ro.sapientia.android_11eloadas.util.Constants

class LoginViewModelFactory(
    private val context: Context,
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(context, repository) as T
    }
}

class LoginViewModel(val context: Context, val repository: TrackerRepository) : ViewModel() {
    // get from API
    var token: MutableLiveData<String> = MutableLiveData()

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            Log.i("xxx", request.toString())
            val result = repository.login(request)
            token.value = result.token

            MyApplication.token = result.token
            // Save the token
            val prefs = context.getSharedPreferences(Constants.MY_PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString("token", token.value)
            prefs.edit().putString("email", request.email)
            prefs.edit().putString("password", request.passwordKey)
            prefs.edit().apply()
            prefs.edit().commit()

            Log.i("xxx", "email ${request.email}")
            Log.i("xxx", "password ${request.passwordKey}")
            Log.i("xxx", "token ${token.value}")
        }
    }
}