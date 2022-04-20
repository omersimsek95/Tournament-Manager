package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tournamentmanager_turnuvaoluturma.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnYeni=findViewById<Button>(R.id.btnYeniTurnuva)
        val btnEski=findViewById<Button>(R.id.btnEskiTurnuvalar)



        btnYeni.setOnClickListener{

            startActivity(Intent(this@MainActivity, TurnuvaOlusturActivity::class.java))


        }
        btnEski.setOnClickListener {

            startActivity(Intent(this@MainActivity, EskiTurnuvalarActivity::class.java))
        }



    }
}