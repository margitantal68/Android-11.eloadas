package ro.sapientia.android_11eloadas.repository

import retrofit2.Response
import ro.sapientia.android_11eloadas.api.RetrofitInstance
import ro.sapientia.android_11eloadas.model.LoginRequest
import ro.sapientia.android_11eloadas.model.LoginResponse
import ro.sapientia.android_11eloadas.model.User

class TrackerRepository {
    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getUsers(token: String): Response<List<User>> {
        return RetrofitInstance.api.getUsers(token)
    }
}