package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import com.example.tournamentmanager_turnuvaoluturma.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnYeni = findViewById<Button>(R.id.btnYeniTurnuva)
        val btnEski = findViewById<Button>(R.id.btnEskiTurnuvalar)
        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        btnYeni.setOnClickListener {

            startActivity(Intent(this@MainActivity, TurnuvaOlusturActivity::class.java))


        }
        btnEski.setOnClickListener {

            startActivity(Intent(this@MainActivity, EskiTurnuvalarActivity::class.java))
        }


    }
}