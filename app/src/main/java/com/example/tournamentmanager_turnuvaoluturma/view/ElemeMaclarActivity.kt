package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.ElemeDao
import com.example.tournamentmanager_turnuvaoluturma.dao.MaclarDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.TurlarAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivityElemeMaclarBinding
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeMaclarClass
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi
import com.example.tournamentmanager_turnuvaoluturma.viewmodel.ElemeMaclarViewModel

import java.util.ArrayList

class ElemeMaclarActivity : AppCompatActivity() {
    private lateinit var emView:ActivityElemeMaclarBinding
    private lateinit var vt: VeriTabaniYardimcisi
    private lateinit var spinnerAdapter: TurlarAdapter
    private lateinit var maclarListesi:ArrayList<ElemeMaclarClass>
    private lateinit var kazananTakimlar:ArrayList<String>
    private lateinit var eslestirilenMaclar:ArrayList<MaclarClass>
    private lateinit var oyuncuListesi: ArrayList<String>
    private lateinit var kazananOyuncuListesi:ArrayList<String>
    private val elemeMaclarVM: ElemeMaclarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emView=DataBindingUtil.setContentView(this,R.layout.activity_eleme_maclar)
        vt= VeriTabaniYardimcisi(this)

        val hafta=intent.getIntExtra("hafta",1)
        val sonElemeid=intent.getIntExtra("sonElemeid",0)
        oyuncuListesi=ArrayList()
        val enBuyukHafta= MaclarDao().sonHaftaGetir(vt,sonElemeid)

        val tabloAd= ElemeDao().elemeTabloAd(vt,sonElemeid)
        emView.txtTurnuvaAdi.text=tabloAd

        emView.rvEleme.layoutManager= LinearLayoutManager(this)
        maclarListesi= ArrayList()
        kazananOyuncuListesi= ArrayList()
        maclarListesi= MaclarDao().elemeMacGetir(vt,sonElemeid,hafta)

        if (maclarListesi.size==8){
            emView.txtFinaller.text="1/16 Turu"
        }
        if (maclarListesi.size==4){
            emView.txtFinaller.text="Çeyrek Final"
        }
        if (maclarListesi.size==2){
            emView.txtFinaller.text="Yarı Final"
        }
        if (maclarListesi.size==1){
            emView.txtFinaller.text="Final"
        }

        for (i in maclarListesi) {
            if (i.takim1_skor != -1) {
                if (i.takim1_skor!! > i.takim2_skor!!) {
                    kazananOyuncuListesi.add(i.takim1_isim)

                }
                if (i.takim1_skor!! < i.takim2_skor!!) {
                    kazananOyuncuListesi.add(i.takim2_isim)
                }
            }
        }

        if (maclarListesi.size==1){
            emView.btnSonrakiTur.setEnabled(false)
            if (kazananOyuncuListesi.size==1){
                Toast.makeText(this,"Kazanan Oyuncu ${kazananOyuncuListesi[0]}",Toast.LENGTH_SHORT).show()

            }
        }

        if (maclarListesi.size!=kazananOyuncuListesi.size){
            emView.btnSonrakiTur.setEnabled(false)
        }
        spinnerAdapter= TurlarAdapter(this,maclarListesi,sonElemeid,hafta,enBuyukHafta)
        emView.rvEleme.adapter=spinnerAdapter
        emView.btnSonrakiTur.setOnClickListener {

            eslestirilenMaclar= ArrayList()
            if (hafta==enBuyukHafta){
                elemeMaclarVM.eslestirmeEleme(kazananOyuncuListesi,hafta+1)
            eslestirilenMaclar=elemeMaclarVM.maclarList
            MaclarDao().elemeMacEkle(vt,eslestirilenMaclar,sonElemeid)}

                val intent2 = Intent(this@ElemeMaclarActivity, ElemeMaclarActivity::class.java)
                intent2.putExtra("sonElemeid", sonElemeid)
                intent2.putExtra("hafta", hafta + 1)
                 finish()
                startActivity(intent2)


        }
        emView.btnAnaSayfa2.setOnClickListener {
            startActivity(Intent(this@ElemeMaclarActivity, MainActivity::class.java))
            finish()
        }
        emView.btnSil2.setOnClickListener {

            val ad= AlertDialog.Builder(this@ElemeMaclarActivity)

            ad.setMessage("Geçerli Turnuva Kayıdı Siliniyor.Emin misiniz?")
            ad.setPositiveButton("Sil"){ dialogInterface,i->

                ElemeDao().elemeTabloSil(vt,sonElemeid)
                startActivity(Intent(this@ElemeMaclarActivity, MainActivity::class.java))
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
            startActivity(Intent(this@ElemeMaclarActivity, MainActivity::class.java))
            finish()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Anasayfaya gitmek için geri tuşuna bir kez daha dokunun", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}