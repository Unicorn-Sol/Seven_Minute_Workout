package com.sevenminuteworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * This method is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)

        // Click event for start Button which we have created in XML.
        llStart.setOnClickListener {
            //TODO(Step 11 - On Start button click navigate it to the Exercise Activity.)
            //START
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
            //END
        }
    }

    fun bmiClicked(view: View) {
        val intent = Intent(this, BmiActivity::class.java)
        startActivity(intent)
    }

    fun historyClicked(view: View) {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }
}