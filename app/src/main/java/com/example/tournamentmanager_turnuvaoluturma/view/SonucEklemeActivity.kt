package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tournamentmanager_turnuvaoluturma.dao.MaclarDao
import com.example.tournamentmanager_turnuvaoluturma.dao.PuanCetveliDao
import com.example.tournamentmanager_turnuvaoluturma.R
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivitySonucEklemeBinding
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi


class SonucEklemeActivity : AppCompatActivity() {
    private lateinit var seView:ActivitySonucEklemeBinding

    private lateinit var vt: VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seView=DataBindingUtil.setContentView(this,R.layout.activity_sonuc_ekleme)
        vt= VeriTabaniYardimcisi(this)
        val takim1=intent.getStringExtra("takim1")!!
        val takim2=intent.getStringExtra("takim2")!!
        val intentTakim1Skor=intent.getIntExtra("takim1_skor",0)
        val intentTakim2Skor=intent.getIntExtra("takim2_skor",0)
        if (intentTakim1Skor!=-1){
            seView.editTxtSonucTakim1.setText(intentTakim1Skor.toString())
            seView.editTxtSonucTakim2.setText(intentTakim2Skor.toString())
        }

        seView.txtSonucTakim1.text=takim1
        seView.txtSonucTakim2.text=takim2


        seView.btnKaydet.setOnClickListener {
            if (( seView.editTxtSonucTakim1.text.isEmpty() ||  seView.editTxtSonucTakim2.text.isEmpty())){
                Toast.makeText(this,"Lütfen Skor Giriniz",Toast.LENGTH_LONG).show()
            }else{
                val takim1Skor=Integer.parseInt( seView.editTxtSonucTakim1.text.toString())
                val takim2Skor=Integer.parseInt( seView.editTxtSonucTakim2.text.toString())
                val sonTabloid=intent.getIntExtra("sonTabloid",0)
                MaclarDao().macGuncelle(vt,takim1,takim2,takim1Skor,takim2Skor,sonTabloid)
                PuanCetveliDao().oyuncuGuncelle(vt,sonTabloid)

                val intent= Intent(this@SonucEklemeActivity, MaclarActivity::class.java)
                intent.putExtra("sonTabloid",sonTabloid)
                intent.putExtra("takim1",takim1)
                intent.putExtra("takim2",takim2)
                intent.putExtra("takim1Skor",takim1Skor)
                intent.putExtra("takim2Skor",takim2Skor)
                finish()

                startActivity(intent)
            }



    }






    }
}