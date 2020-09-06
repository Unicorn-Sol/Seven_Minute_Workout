package com.sevenminuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.back_button_custom_dialog.*
import java.util.*
import kotlin.collections.ArrayList

//TODO(Step 1 - Add an ExerciseActivity.)-->
class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0

    private lateinit var list : ArrayList<ExerciseModel>
    private var currentPosition = -1

    private lateinit var tts : TextToSpeech
    private lateinit var player:MediaPlayer

    private lateinit var exerciseStatusAdapter : ExerciseStatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        list = Constants.getExercises()
        tts = TextToSpeech(this, this)
        player = MediaPlayer()

        setSupportActionBar(toolbar_exercise_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        // Us the back pressed (close the activity) functionality when clicking the toolbar back button
        toolbar_exercise_activity.setNavigationOnClickListener {
            customDialogueBackButton()
        }
        //END
        setRestView()
        setUpExerciseStatusRecyclerView()

    }

    override fun onBackPressed() {
        customDialogueBackButton()

    }


    override fun onDestroy() {
        super.onDestroy()
        restTimer?.cancel()
        restProgress = 0
        exerciseTimer?.cancel()
        exerciseProgress = 0
        tts.stop()
        tts.shutdown()
    }
    private fun setProgressBar(){

            currentPosition++
            restProgress = 0
            progressBar.progress = restProgress
            upcomingExercise.text = list[currentPosition].getName()
            restTimer = object : CountDownTimer(5000, 1000){
                override fun onFinish() {
                    llRestView.visibility = View.GONE
                    llExerciseView.visibility = View.VISIBLE

                    list[currentPosition].setIsSelected(true)
                    exerciseStatusAdapter.notifyDataSetChanged()
                    setExerciseView()

                }

                override fun onTick(millisUntilFinished: Long) {
                    restProgress++
                    progressBar.progress = 5 - restProgress
                    tvTimer.text = (5 - restProgress).toString()
                }

            }.start()


    }

    private fun setRestView(){

        try {

            player = MediaPlayer.create(this, R.raw.press_start)
            player.isLooping = false
            player.start()

        }catch (e : Exception){
            e.printStackTrace()
        }
        restTimer?.cancel()
        restProgress = 0
        setProgressBar()
    }

    private fun setExerciseView(){
        exerciseTimer?.cancel()
        exerciseProgress= 0
        setProgressBar2()
    }

    private fun setProgressBar2() {
        exerciseProgress = 0
        progressBar2.progress = exerciseProgress
        exerciseImage.setImageResource(list[currentPosition].getImage())
        exerciseText.text = list[currentPosition].getName()
        speakOut(list[currentPosition].getName())
        exerciseTimer = object : CountDownTimer(5000, 1000){
            override fun onFinish() {
                if (currentPosition<11){
                Toast.makeText(this@ExerciseActivity, "Lets take some rest", Toast.LENGTH_SHORT).show()
                llRestView.visibility = View.VISIBLE
                llExerciseView.visibility = View.GONE
                    list[currentPosition].setIsCompleted(true)
                    list[currentPosition].setIsSelected(false)
                    exerciseStatusAdapter.notifyDataSetChanged()
                setRestView()
                }
                 else{
                 Toast.makeText(this@ExerciseActivity , "you have successfully completed exercises", Toast.LENGTH_SHORT).show()
                  val intent = Intent(this@ExerciseActivity , MainActivity::class.java)
                startActivity(intent)
                 }

            }

            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                progressBar2.progress = 5 - exerciseProgress
                tvTimer2.text = (5 - exerciseProgress).toString()
            }

        }.start()
    }

    override fun onInit(status: Int) {
       if(status == TextToSpeech.SUCCESS){

           val result = tts.setLanguage(Locale.US)
           if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
               Log.e("tts", "Language Problem")
           }
       }else{
           Log.e("tts", "Text to speech is not installed")
       }
    }

    private fun speakOut(text : String){
        tts.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun setUpExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        exerciseStatusAdapter = ExerciseStatusAdapter(this, list)
        rvExerciseStatus.adapter = exerciseStatusAdapter
    }

    private fun customDialogueBackButton(){
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.back_button_custom_dialog)
        customDialog.tvYes.setOnClickListener {
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener {
            customDialog.dismiss()
        }
        customDialog.show()
    }
}