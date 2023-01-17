package com.limadoug.tasks.service.listener

interface APIListener<T> {
    fun onSucess(result: T)
    fun onFailure(message: String)
}