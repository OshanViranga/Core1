package com.example.core1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showScore = findViewById<TextView>(R.id.show_score)

        val score = findViewById<Button>(R.id.score)
        score.setOnClickListener {
            count = score(showScore.text.toString())
            var mediaPlayer = MediaPlayer.create(this, R.raw.bell )
            if (count in 0..15) {
                showScore.setText(count.toString())
                if (count in 0..4)
                    showScore.setTextColor(Color.parseColor("#000000"))
                if (count in 5..9)
                    showScore.setTextColor(Color.parseColor("#0000FF"))
                else if (count in 10..15)
                    showScore.setTextColor(Color.parseColor("#00FF00"))
                    if (count == 15)
                        mediaPlayer.start()
            }
        }

        val steal = findViewById<Button>(R.id.steal)
        steal.setOnClickListener {
            count = steal(showScore.text.toString())
            if (count in 0..15) {
                showScore.setText(count.toString())
                if (count in 0..4)
                    showScore.setTextColor(Color.parseColor("#000000"))
                if (count in 5..9)
                    showScore.setTextColor(Color.parseColor("#0000FF"))
                else if (count in 10..15)
                    showScore.setTextColor(Color.parseColor("#00FF00"))
            }
        }

        val reset = findViewById<Button>(R.id.reset)
        reset.setOnClickListener {
            showScore.setText("0")
        }
    }

    private fun score(show_score: String) = show_score.toInt() + 1

    private fun steal(show_score: String) = show_score.toInt() - 1

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val showScore = findViewById<TextView>(R.id.show_score)
        val score = savedInstanceState.getInt("score")
        showScore.setText(score.toString())

    }
}