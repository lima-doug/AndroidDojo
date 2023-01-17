package com.limadoug.tasks.service.model

class ValidationModel(message: String = "") {

    private var status: Boolean = true
    private lateinit var validationMessage: String

    init {
        if(message != ""){
            validationMessage = message
            status = false
        }
    }

    fun status() = status
    fun message() = validationMessage

    }