package com.limadoug.tasks.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.google.gson.Gson
import com.limadoug.tasks.service.listener.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    fun failureResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> handleResponse(response: Response<T>, listener: APIListener<T>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
            response.body()?.let { listener.onSucess(it) }
        } else {
            listener.onFailure(failureResponse(response.errorBody()!!.string()))
        }
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                handleResponse(response, listener)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            //            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
//                handleResponse(response, listener)
//            }
//
//            override fun onFailure(call: Call<Boolean>, t: Throwable) {
//                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
//            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isConnectionAvaiable(): Boolean{
        var result = false

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNet = cm.activeNetwork ?: return false
        val netWorkCapabilites = cm.getNetworkCapabilities(activeNet) ?: return false

        result = when{
        netWorkCapabilites.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        netWorkCapabilites.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
        }

        return result

    }

}