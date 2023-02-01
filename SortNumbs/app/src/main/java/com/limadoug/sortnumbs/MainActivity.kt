package com.limadoug.sortnumbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.limadoug.sortnumbs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonSort.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_sort){
            sortNumbers()
        }
    }

    private fun sortNumbers(){
        val randomNumber = (1..10).random()
        binding.textSortNumber.text = randomNumber.toString()

    }
}