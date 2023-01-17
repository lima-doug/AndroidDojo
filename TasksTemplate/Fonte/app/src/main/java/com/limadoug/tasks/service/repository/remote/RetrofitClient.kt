package com.limadoug.tasks.service.repository.remote

import android.app.Person
import com.devmasterteam.tasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {



    //Singleton
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val Url = "http://devmasterteam.com/CursoAndroidAPI/"
        private var token: String = ""
        private var personKey: String = ""



        private fun getRetrofitInstance(): Retrofit {
            val httpCliente = OkHttpClient.Builder()

            httpCliente.addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                        .newBuilder()
                        .addHeader(TaskConstants.HEADER.TOKEN_KEY, token)
                        .addHeader(TaskConstants.HEADER.PERSON_KEY, personKey)
                        .build()

                    return chain.proceed(request)
                }

            })

            if(!::INSTANCE.isInitialized){
                synchronized(RetrofitClient::class){
                INSTANCE = Retrofit.Builder()
                    .baseUrl(Url)
                    .client(httpCliente.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                }
            }

            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>): T{
            return getRetrofitInstance().create(serviceClass)
        }

        fun addHeader(tokenValue: String, personKeyValue: String) {
            token = tokenValue
            personKey = personKeyValue

        }
    }
}