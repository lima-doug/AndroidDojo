package com.limadougg.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.limadougg.convidados.model.GuestModel
import com.limadougg.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepository = GuestRepository(application.applicationContext)
    private val listAllGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuest

    fun getAll(){
        listAllGuest.value = repository.getAll()
    }

    fun getAbsent(){
        listAllGuest.value = repository.getAbsent()
    }

    fun getPresent(){
        listAllGuest.value = repository.getPresence()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }
}