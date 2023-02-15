package com.limadoug.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel (private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        QuestionModel(R.string.question_australia, true),
        QuestionModel(R.string.question_oceans, true),
        QuestionModel(R.string.question_mideast, false),
        QuestionModel(R.string.question_africa, false),
        QuestionModel(R.string.question_americas, true),
        QuestionModel(R.string.question_asia, true)
    )

    private var auxDisableButtons = 0

    private var _currentIndex: Int
        get() = savedStateHandle[Constants.KEY.CURRENT_INDEX_KEY] ?: 0 // same = savedStateHandle.get(Constants.KEY.CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(Constants.KEY.CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[_currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[_currentIndex].textResId

    fun moveToNext() {
        _currentIndex = (_currentIndex + 1) % questionBank.size
    }

    fun currentIndex(): Int{
        return _currentIndex
    }

    fun getDisable(): Int{
        return auxDisableButtons
    }

    fun toDisable() {
        auxDisableButtons ++;
    }

    fun clearDisable(){
        auxDisableButtons = 0;
    }

}