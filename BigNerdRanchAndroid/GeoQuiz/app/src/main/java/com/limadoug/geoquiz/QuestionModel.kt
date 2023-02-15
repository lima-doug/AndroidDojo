package com.limadoug.geoquiz

import androidx.annotation.StringRes

data class QuestionModel(@StringRes val textResId: Int, val answer: Boolean)
