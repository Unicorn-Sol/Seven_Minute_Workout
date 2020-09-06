package com.sevenminuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbar_exercise_activity
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)


        setSupportActionBar(toolbar_exercise_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }


        buttonCalculate.setOnClickListener {
            val w = weightTE.text.toString().toFloat()
            val h = heightTE.text.toString().toFloat()
           if (isValid()){
               val ans = ((w*10000)/(h*h)).toString()
               val bmiValue = BigDecimal(ans.toDouble()).setScale(2,RoundingMode.HALF_EVEN)

               bmiText.text = bmiValue.toString()
           }

        }
    }

    private fun isValid(): Boolean{
        var isValid = true

        if (heightTE.text.isNullOrEmpty())
            isValid = false
        else if(weightTE.text.isNullOrEmpty())
            isValid =false

        return isValid
    }
}