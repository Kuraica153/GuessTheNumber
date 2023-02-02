package kuraica.christopher.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var maxValue: Int = 100
    var minValue: Int = 0
    var num: Int = 0
    var haveWon: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the UI elements
        val guessing: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: TextView = findViewById(R.id.guessed)

        // Set event listeners
        generate.setOnClickListener {
            num = Random.nextInt(0, 100)
            guessing.text = "Is it $num?"
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        down.setOnClickListener {
            maxValue = num
            if(checkingLimits()){
                num = Random.nextInt(minValue, maxValue)
                guessing.text = "Is it $num?"
            } else {
                guessing.text = "No puede ser :( me ganaste"
            }
        }

        up.setOnClickListener {
            minValue = num
            if(checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessing.text = "Is it $num?"
            } else {
                guessing.text = "No puede ser :( me ganaste"
            }
        }

        guessed.setOnClickListener {
            if(!haveWon){
                haveWon = true
                guessing.text = "Adviné tu numero: $num!"
                guessed.text = "Jugar de nuevo"
                haveWon = true
            } else {
                generate.visibility = View.VISIBLE
                guessing.text = "Tap generate to start"
                guessed.text = "Guessed"
                guessed.visibility = View.GONE
                resetValues()
            }
        }

    }

    private fun resetValues(){
        maxValue = 100
        minValue = 0
        num = 0
        haveWon = false
    }

    private fun checkingLimits(): Boolean{
        return minValue != maxValue
    }

}