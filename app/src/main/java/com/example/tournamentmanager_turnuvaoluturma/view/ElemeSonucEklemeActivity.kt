package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.tournamentmanager_turnuvaoluturma.dao.MaclarDao
import com.example.tournamentmanager_turnuvaoluturma.R
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivityElemeSonucEklemeBinding
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi


class ElemeSonucEklemeActivity : AppCompatActivity() {
    private lateinit var esView:ActivityElemeSonucEklemeBinding

    private lateinit var vt: VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        esView=DataBindingUtil.setContentView(this,R.layout.activity_eleme_sonuc_ekleme)
        vt= VeriTabaniYardimcisi(this)
        val takim1=intent.getStringExtra("takim1")!!
        val takim2=intent.getStringExtra("takim2")!!
        val intentTakim1Skor=intent.getIntExtra("takim1_skor",0)
        val intentTakim2Skor=intent.getIntExtra("takim2_skor",0)
        val intentHafta=intent.getIntExtra("hafta",1)
        if (intentTakim1Skor!=-1) {
            esView.editTxtSonucTakim1.setText(intentTakim1Skor.toString())
            esView.editTxtSonucTakim2.setText(intentTakim2Skor.toString())
        }
        esView.txtSonucTakim1.text=takim1
        esView.txtSonucTakim2.text=takim2



        esView.btnKaydet.setOnClickListener {
            if ((esView.editTxtSonucTakim1.text.isEmpty() || esView.editTxtSonucTakim2.text.isEmpty())){
                Toast.makeText(this,"Lütfen Skor Giriniz",Toast.LENGTH_LONG).show()
            }else{
                val takim1Skor=Integer.parseInt(esView.editTxtSonucTakim1.text.toString())
                val takim2Skor=Integer.parseInt(esView.editTxtSonucTakim2.text.toString())
                if (takim1Skor!=takim2Skor){

                val sonElemeid=intent.getIntExtra("sonElemeid",0)
                MaclarDao().elemeMacGuncelle(vt,takim1,takim2,takim1Skor,takim2Skor,sonElemeid,intentHafta)

                val intent= Intent(this@ElemeSonucEklemeActivity, ElemeMaclarActivity::class.java)
                intent.putExtra("sonElemeid",sonElemeid)
                intent.putExtra("takim1",takim1)
                intent.putExtra("takim2",takim2)
                intent.putExtra("hafta",intentHafta)
                intent.putExtra("takim1Skor",takim1Skor)
                intent.putExtra("takim2Skor",takim2Skor)
                finish()

                startActivity(intent)
            }else{
                Toast.makeText(this,"Eleme Maçları Berabere Bitemez",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}