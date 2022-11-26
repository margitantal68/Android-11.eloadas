package ro.sapientia.android_11eloadas.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.sapientia.android_11eloadas.MyApplication
import ro.sapientia.android_11eloadas.model.User
import ro.sapientia.android_11eloadas.repository.TrackerRepository
import java.lang.Exception


class UserListViewModelFactory(
    private val context: Context,
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(context, repository) as T
    }
}

class UserListViewModel(val context: Context, val repository: TrackerRepository) : ViewModel() {
    var userList = MutableLiveData<MutableList<User>>()
    var contentChange = MutableLiveData<Int>()

    fun readUsers() {
        userList.value = mutableListOf()
        contentChange.value = 0
        viewModelScope.launch {
            try {
                userList.postValue(repository.getUsers(MyApplication.token).toMutableList())
                contentChange.postValue(1)
            } catch (e: Exception) {
                Log.i("xxx", e.toString())
            }
        }
    }
}