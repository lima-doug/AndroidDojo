package com.limadoug.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.limadoug.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private val questionBank = listOf(
        QuestionModel(R.string.question_australia, true),
        QuestionModel(R.string.question_oceans, true),
        QuestionModel(R.string.question_mideast, false),
        QuestionModel(R.string.question_africa, false),
        QuestionModel(R.string.question_americas, true),
        QuestionModel(R.string.question_asia, true)
    )

    private var currentIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}