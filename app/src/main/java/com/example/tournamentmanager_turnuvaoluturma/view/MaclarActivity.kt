package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.MaclarDao
import com.example.tournamentmanager_turnuvaoluturma.dao.PuanCetveliDao
import com.example.tournamentmanager_turnuvaoluturma.dao.TabloDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.HaftalarAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivityMaclarBinding
import com.example.tournamentmanager_turnuvaoluturma.model.Maclar
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi
import java.util.ArrayList


class MaclarActivity : AppCompatActivity() {
    private lateinit var maclarAdapter: HaftalarAdapter
    private lateinit var vt: VeriTabaniYardimcisi
    private lateinit var maclar:ArrayList<MaclarClass>
    private lateinit var maclarListesi: ArrayList<Maclar>
    private lateinit var maView:ActivityMaclarBinding


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        maView=DataBindingUtil.setContentView(this,R.layout.activity_maclar)


        vt= VeriTabaniYardimcisi(this)
        maView.rvHaftalar.layoutManager= LinearLayoutManager(this,GridLayoutManager.VERTICAL,false)
        maView.rvHaftalar.setHasFixedSize(true)

        var sonTabloid=intent.getIntExtra("sonTabloid",0)

        var takim1=intent.getStringExtra("takim1")
        var takim2=intent.getStringExtra("takim2")
        var takim1Skor=intent.getIntExtra("takim1Skor",0)
        var takim2Skor=intent.getIntExtra("takim2Skor",0)
        maclarListesi=ArrayList()
        maclarListesi= MaclarDao().macGetir(vt,sonTabloid)

        val tabloAd= TabloDao().tabloAd(vt,sonTabloid)
        maView.txtTurnuvaMaclar.text=tabloAd

        var oyuncuSayisi= PuanCetveliDao().tumOyuncular(vt,sonTabloid).size
        var matchPerRound:Int=oyuncuSayisi/2
        if (oyuncuSayisi%2==1){
            matchPerRound++
        }

        maclarAdapter= HaftalarAdapter(this,maclarListesi,sonTabloid,matchPerRound)
        maView.rvHaftalar.adapter=maclarAdapter

        maView.btnPuanDurumu.setOnClickListener {
            val intent=Intent(this@MaclarActivity, PuanCetveliActivity::class.java)
            intent.putExtra("sonTabloid",sonTabloid)
            startActivity(intent)
        }
        maView.btnAnaSayfa3.setOnClickListener {
            startActivity(Intent(this@MaclarActivity, MainActivity::class.java))
            finish()
        }
        maView.btnSil3.setOnClickListener {

            val ad= AlertDialog.Builder(this@MaclarActivity)

            ad.setMessage("Geçerli Turnuva Kayıdı Siliniyor.Emin misiniz?")
            ad.setPositiveButton("Sil"){ dialogInterface,i->

                TabloDao().tabloSil(vt,sonTabloid)
                startActivity(Intent(this@MaclarActivity, MainActivity::class.java))
                finish()
            }
            ad.setNegativeButton("İptal"){ dialogInterface,i->
            }
            ad.create().show()
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startActivity(Intent(this@MaclarActivity, MainActivity::class.java))
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Anasayfaya gitmek için geri tuşuna bir kez daha dokunun", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }



}






