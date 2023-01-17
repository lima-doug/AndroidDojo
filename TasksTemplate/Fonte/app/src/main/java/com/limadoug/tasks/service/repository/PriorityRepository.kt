package com.limadoug.tasks.service.repository




import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.devmasterteam.tasks.R
import com.limadoug.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.limadoug.tasks.service.repository.remote.PriorityService
import com.limadoug.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val db = TaskDatabase.getDatabase(context).priorityDAO()


    companion object{
        private val cache = mutableMapOf<Int, String>()
        fun getDescripton(id: Int): String{
            return cache[id] ?: ""

        }
        fun setDescriotion(id: Int, str: String){
            cache[id] = str
        }
    }

    fun getDescription(id: Int): String{

        val cached = getDescripton(id)

        return if(cached == ""){
            val description = db.getDescription(id)
            setDescriotion(id, description)
            description
        } else {
            cached
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun list(listener: APIListener<List<PriorityModel>>) {
        if (!isConnectionAvaiable()) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.list(), listener)

    }

    fun list(): List<PriorityModel> {
        return db.list()
    }

    fun save(list: List<PriorityModel>) {
        db.clear()
        db.save(list)
    }

}