package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.PuanCetveliDao
import com.example.tournamentmanager_turnuvaoluturma.dao.TabloDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.PuanCetveliAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivityPuanCetveliBinding
import com.example.tournamentmanager_turnuvaoluturma.model.PuanCetveli
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi


class PuanCetveliActivity : AppCompatActivity() {

    private lateinit var oyuncuList:ArrayList<PuanCetveli>
    private lateinit var vt: VeriTabaniYardimcisi
    private lateinit var puanCetveliAdapter: PuanCetveliAdapter
    private lateinit var pcView:ActivityPuanCetveliBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pcView=DataBindingUtil.setContentView(this,R.layout.activity_puan_cetveli)
        vt= VeriTabaniYardimcisi(this)
        val sonTabloid=intent.getIntExtra("sonTabloid",0)
        val tabloAd= TabloDao().tabloAd(vt,sonTabloid)
        pcView.txtTurnuva.text=tabloAd
        Log.e("Puan  Cetveli Acti",sonTabloid.toString())

        oyuncuList= ArrayList<PuanCetveli>()
        oyuncuList= PuanCetveliDao().tumOyuncular(vt,sonTabloid)


        pcView.rvPuanCetveli.layoutManager= LinearLayoutManager(this)
        puanCetveliAdapter= PuanCetveliAdapter(this,oyuncuList)
        pcView.rvPuanCetveli.adapter=puanCetveliAdapter
        pcView.btnFikstur.setOnClickListener {
            val intent= Intent(this@PuanCetveliActivity, MaclarActivity::class.java)
            intent.putExtra("sonTabloid",sonTabloid)
            startActivity(intent)

        }
        pcView.btnAnaSayfa.setOnClickListener {
            startActivity(Intent(this@PuanCetveliActivity, MainActivity::class.java))
            finish()
        }
        pcView.btnSil.setOnClickListener {
            val ad=AlertDialog.Builder(this@PuanCetveliActivity)

            ad.setMessage("Geçerli Turnuva Kayıdı Siliniyor.Emin misiniz?")
            ad.setPositiveButton("Sil"){ dialogInterface,i->

                TabloDao().tabloSil(vt,sonTabloid)
                startActivity(Intent(this@PuanCetveliActivity, MainActivity::class.java))
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
            startActivity(Intent(this@PuanCetveliActivity, MainActivity::class.java))
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Anasayfaya gitmek için geri tuşuna bir kez daha dokunun", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}