package com.limadoug.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.limadoug.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        Log.d(Constants.TAG.TAG_MAIN, "Got a viewModel: $mainViewModel")

        setListeners()
        updateQuestion()

        setContentView(binding.root)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_true -> checkAnswer(true)
            R.id.button_false -> checkAnswer(false)
            R.id.button_nextQuestion -> {
                mainViewModel.moveToNext()
                updateQuestion()
            }
            R.id.button_previousQuestion -> {
                if(mainViewModel.currentIndex() != 0) {
                    mainViewModel.moveToNext()
                    updateQuestion()
                } else {
                    messageToast(getString(R.string.start_list))
                }
            }
            /*R.id.button_false -> {
                val snackbar = Snackbar.make(binding.buttonFalse, "Incorrect", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }*/
        }

    }

    private fun setListeners() {
        binding.buttonTrue.setOnClickListener(this)
        binding.buttonFalse.setOnClickListener(this)
        binding.buttonNextQuestion.setOnClickListener(this)
        binding.buttonPreviousQuestion.setOnClickListener(this)
    }

    private fun updateQuestion(){
        mainViewModel.clearDisable()
        val questionTextResId = mainViewModel.currentQuestionText
        binding.textQuestions.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = mainViewModel.currentQuestionAnswer

        if(mainViewModel.getDisable() == 0) {
            if (userAnswer == correctAnswer) {
                messageToast(getString(R.string.correct_toast))
            } else {
                messageToast(getString(R.string.incorrect_toast))
            }
        } else {
            messageToast(getString(R.string.answer_question))
        }


        mainViewModel.toDisable()

    }

    private fun messageToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}