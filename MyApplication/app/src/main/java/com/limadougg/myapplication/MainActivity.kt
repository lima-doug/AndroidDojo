package com.limadougg.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val biometricAvaiable = BiometricHelper.isBiometricAvaiable(this)

        if(biometricAvaiable){

            val executor = ContextCompat.getMainExecutor(this)

            val bio = BiometricPrompt(this, executor, object: BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                }
            })

            val info = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Titulo")
                .setSubtitle("Subtitle")
                .setDescription("Description")
                .setNegativeButtonText("")
                .build()


            bio.authenticate(info)
        }
    }
}