package com.limadougg.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.limadougg.motivation.infra.MotivationCostants
import com.limadougg.motivation.R
import com.limadougg.motivation.data.Mock
import com.limadougg.motivation.infra.SecurityPreferences
import com.limadougg.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId: Int = MotivationCostants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase()

        //Events
        setListeners()

    }

    private fun setListeners(){
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        }
    }
    private fun handleNextPhrase() {
        val phrase = Mock().getPhrase(categoryId)
        binding.textMotivation.text = phrase
    }
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationCostants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"

    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.blue_inactive))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.blue_inactive))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.blue_inactive))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white_actived))
                categoryId = MotivationCostants.FILTER.ALL

            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white_actived))
                categoryId = MotivationCostants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white_actived))
                categoryId = MotivationCostants.FILTER.SUNNY
            }
        }

    }
}