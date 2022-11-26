package ro.sapientia.android_11eloadas.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ro.sapientia.android_11eloadas.model.LoginRequest
import ro.sapientia.android_11eloadas.model.LoginResponse
import ro.sapientia.android_11eloadas.model.User
import ro.sapientia.android_11eloadas.util.Constants

interface TrackerApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Constants.GET_USERS_URL)
    suspend fun getUsers(@Header("token") token: String): List<User>

}