package com.devmasterteam.tasks.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.limadoug.tasks.service.listener.APIListener
import com.limadoug.tasks.service.model.PersonModel
import com.limadoug.tasks.service.model.ValidationModel
import com.limadoug.tasks.service.repository.BaseRepository
import com.limadoug.tasks.service.repository.PersonRepository
import com.limadoug.tasks.service.repository.SecurityPreferences
import com.limadoug.tasks.service.repository.remote.RetrofitClient

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)
    private val _user = MutableLiveData<ValidationModel>()
    val user: LiveData<ValidationModel> = _user

    @RequiresApi(Build.VERSION_CODES.M)
    fun create(name: String, email: String, password: String) {
        personRepository.create(name, email, password, object : APIListener<PersonModel>{
            override fun onSucess(result: PersonModel) {
                securityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
                securityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
                securityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

                RetrofitClient.addHeader(result.token, result.personKey)

                _user.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _user.value = ValidationModel(message)

            }

        })
    }

}