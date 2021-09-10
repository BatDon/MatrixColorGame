package com.example.matrixcolorgame

import android.graphics.Color
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity

import android.util.Log
import android.widget.Toast
import java.util.Collections.addAll
import com.example.matrixcolorgame.databinding.ActivityMainBinding
import android.graphics.drawable.ColorDrawable

import android.graphics.drawable.Drawable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DefaultItemAnimator

import androidx.recyclerview.widget.LinearLayoutManager

import android.R
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {


    private lateinit var colorList:ArrayList<String>
    private lateinit var matrixColorAdapter: MatrixColorAdapter
    lateinit var activityMainBinding: ActivityMainBinding
    private var correctScore=0
    private var incorrectScore=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        createColorList()
        createAdapter()
        startCountdownTimer()
    }



    private fun createColorList(){
        colorList =arrayListOf<String>()
        colorList.addAll(listOf(black, blue, cyan, dkgray, gray, green, ltgray, magenta, red, white))
        colorList.shuffle()
    }

    private fun createAdapter(){
        var colorCodeCurrent:Int

        matrixColorAdapter = MatrixColorAdapter(colorList).also { it ->
            it.onColorColumn1Click = { color ->
                var colorCodePrevious:Int=0

                val background: Drawable = activityMainBinding.colorChoice.getBackground()
                if (background is ColorDrawable) colorCodePrevious = background.color
                else Log.e("ERROR","Color code doesn't exist")

                colorCodeCurrent=getRandomColorCode(color)


                if(colorCodePrevious==colorCodeCurrent){
                    correctScore++
                    activityMainBinding.correct.setText("Correct: ${correctScore.toString()}")
                }else{
                    incorrectScore++
                    activityMainBinding.incorrect.setText("Incorrect: ${incorrectScore.toString()}")
                }

                activityMainBinding.colorChoice.setBackgroundColor(colorCodeCurrent)
                createColorList()
                it.updateColorList(colorList)
                it.notifyDataSetChanged()
                //randomColor()
            }
            it.onChooseRandomColor = { randomNumber() }

//            it.onChooseRandomColor = { randomNumber(color) }
        }

        activityMainBinding.colorsRecyclerView.setLayoutManager(LinearLayoutManager(this))
        activityMainBinding.colorsRecyclerView.setAdapter(matrixColorAdapter)

    }

        companion object{
            val black="black"
            val blue="blue"
            val cyan="cyan"
            val dkgray="dkgray"
            val gray="gray"
            val green="green"
            val ltgray="ltgray"
            val magenta="magenta"
            val red="red"
            val white="white"
        }



    private fun getRandomColorCode(color:String):Int{

        return when (color) {
            black -> BLACK
            blue -> BLUE
            cyan -> CYAN
            dkgray -> DKGRAY
            gray -> GRAY
            green -> GREEN
            ltgray -> LTGRAY
            magenta -> MAGENTA
            red -> RED
            white -> WHITE

            else -> { // Note the block
                Log.e("ERROR", "Can only be 8 different colors")
            }
        }
    }

    fun randomNumber ():Int{
        Log.i("randomNumber", "called")
        val colorString=colorList[Random.nextInt(9)]
        return getRandomColorCode(colorString)
    }

//    fun randomColor():Int {
//        val randomColorIndex=Random.nextInt(9)
//        val randomColorString= colorList[randomColorIndex]
//        return getRandomColorCode(randomColorString)
//    }

    private fun startCountdownTimer() {
        object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdownTimer.setText(":${millisUntilFinished / 1000}")
                //here you can have your logic to set text to edittext
            }

            override fun onFinish() {
                countdownTimer.setText(":0")
                showAlertDialog()
            }
        }.start()
    }

    private fun showAlertDialog(){
        AlertDialog.Builder(this)
            .setTitle("Finished")
            .setMessage("End of Game").show()
    }


}