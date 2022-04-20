package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tournamentmanager_turnuvaoluturma.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class EskiTurnuvalarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eski_turnuvalar)
        val btnAnaSayfa4=findViewById<Button>(R.id.btnAnaSayfa4)
        val bottomNav=findViewById<BottomNavigationView>(R.id.bottomNav)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        NavigationUI.setupWithNavController(bottomNav,navHostFragment.navController)
        btnAnaSayfa4.setOnClickListener {

            startActivity(Intent(this@EskiTurnuvalarActivity, MainActivity::class.java))

        }
    }
}