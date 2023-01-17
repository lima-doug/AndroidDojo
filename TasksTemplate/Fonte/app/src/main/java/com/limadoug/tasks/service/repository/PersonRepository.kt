package com.limadoug.tasks.service.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.google.gson.Gson
import com.limadoug.tasks.service.listener.APIListener
import com.limadoug.tasks.service.model.PersonModel
import com.limadoug.tasks.service.repository.remote.PersonService
import com.limadoug.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PersonService::class.java)

    @RequiresApi(Build.VERSION_CODES.M)
    fun login(email: String, password: String, listener: APIListener<PersonModel>) {
        val call = remote.login(email, password)

        if (!isConnectionAvaiable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(call, listener)

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun create(name: String, email: String, password: String, listener: APIListener<PersonModel>) {
        val call = remote.create(name, email, password)

        if (!isConnectionAvaiable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        executeCall(call, listener)
    }
}