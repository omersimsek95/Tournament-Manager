package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.ElemeDao
import com.example.tournamentmanager_turnuvaoluturma.dao.MaclarDao
import com.example.tournamentmanager_turnuvaoluturma.dao.PuanCetveliDao
import com.example.tournamentmanager_turnuvaoluturma.dao.TabloDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.OyuncuEkleAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.ActivityTurnuvaOlusturBinding
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi
import com.example.tournamentmanager_turnuvaoluturma.viewmodel.TurnuvaOlusturViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TurnuvaOlusturActivity : AppCompatActivity() {
    private lateinit var toView:ActivityTurnuvaOlusturBinding
    private lateinit var oyuncu_isimList: ArrayList<String>
    private lateinit var spinnerAdapter: OyuncuEkleAdapter
    private val turnuvaOlusturViewModel:TurnuvaOlusturViewModel by viewModels()
    private lateinit var oyuncuListesi: ArrayList<String>
    private lateinit var maclarListesi: ArrayList<MaclarClass>
    private lateinit var eslestirilenMaclar:ArrayList<MaclarClass>
    private lateinit var oyuncuAdlari:ArrayList<String>
    private lateinit var veriAdaptoru:ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       toView=DataBindingUtil.setContentView(this,R.layout.activity_turnuva_olustur)

        val vt = VeriTabaniYardimcisi(this)

        toView.rvOyuncuEkleme.layoutManager= LinearLayoutManager(this)
        oyuncu_isimList= ArrayList()
        oyuncuAdlari= ArrayList()
        var spinner_count=0
        oyuncuAdlari= PuanCetveliDao().tumOyuncuAdlari(vt)
        veriAdaptoru=ArrayAdapter(this@TurnuvaOlusturActivity,android.R.layout.simple_list_item_1,android.R.id.text1,oyuncuAdlari)
        toView.spinner.adapter=veriAdaptoru
        toView.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (spinner_count == 0)
                {
                    spinner_count++
                }
                else
                {
                    oyuncu_isimList.add(oyuncuAdlari.get(position))
                    spinnerAdapter = OyuncuEkleAdapter(this@TurnuvaOlusturActivity, oyuncu_isimList)
                    toView.rvOyuncuEkleme.adapter = spinnerAdapter
                    toView.editTxtOyuncuAdi2.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        toView.buttonEkle.setOnClickListener {


            if (toView.editTxtOyuncuAdi2.text.isEmpty()) {
                Toast.makeText(this, "Lütfen İsim Giriniz", Toast.LENGTH_SHORT).show()
            } else {
                oyuncu_isimList.add(toView.editTxtOyuncuAdi2.text.toString())
                spinnerAdapter = OyuncuEkleAdapter(this, oyuncu_isimList)
                toView.rvOyuncuEkleme.adapter = spinnerAdapter
                toView.editTxtOyuncuAdi2.setText("")
            }
        }

        toView.imgBtnileri.setOnClickListener {
            //Turnuva adı edittext boş kontrolü
            if (toView.editTxtTurnuvaAdi.text.isEmpty()){
                Toast.makeText(this,"Turnuva Adı Giriniz",Toast.LENGTH_SHORT).show()
            }
            else{

            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            val turnuvaAdi = toView.editTxtTurnuvaAdi.text.toString()

            oyuncuListesi=ArrayList()
            oyuncuListesi=spinnerAdapter.geriDondur()
            if (oyuncuListesi.size==oyuncuListesi.distinct().count()){
                if (toView.radioButtonTek.isChecked){
                    if (oyuncuListesi.size<=32 && oyuncuListesi.size>=3){
                        TabloDao().tabloEkle(vt, turnuvaAdi,currentDate)
                        val sonTabloid = TabloDao().sonTablo_id(vt)

                        for(i in oyuncuListesi){
                            PuanCetveliDao().oyuncuEkle(vt,i,sonTabloid)
                        }

                        maclarListesi= ArrayList()
                        turnuvaOlusturViewModel.eslestirmeTek(oyuncuListesi)
                        maclarListesi=turnuvaOlusturViewModel.maclar
                        MaclarDao().macEkle(vt,maclarListesi,sonTabloid)

                        val intent = Intent(this@TurnuvaOlusturActivity, PuanCetveliActivity::class.java)
                        intent.putExtra("sonTabloid", sonTabloid)
                        finish()
                        startActivity(intent)

                   }else{
                        Toast.makeText(this,"Lig seçeneklerinde en az 3 en fazla 32 sayıda oyuncu olmalıdır.",Toast.LENGTH_LONG).show()
                   }

                }else if (toView.radioButtonEleme.isChecked){
                    if ((oyuncuListesi.size%32==0 || oyuncuListesi.size%16==0 || oyuncuListesi.size%8==0 || oyuncuListesi.size%4==0 ) && oyuncuListesi.size>=3 && oyuncuListesi.size<=32){
                        ElemeDao().elemeTabloEkle(vt,turnuvaAdi,currentDate)

                        val sonElemeid= ElemeDao().sonElemeidGetir(vt)
                        eslestirilenMaclar= java.util.ArrayList()
                        turnuvaOlusturViewModel.eslestirmeEleme(oyuncuListesi,1)
                        eslestirilenMaclar=turnuvaOlusturViewModel.maclar
                        MaclarDao().elemeMacEkle(vt,eslestirilenMaclar,sonElemeid)

                       val intent = Intent(this@TurnuvaOlusturActivity, ElemeMaclarActivity::class.java)
                        intent.putExtra("sonElemeid", sonElemeid)
                        intent.putExtra("kazananOyuncuListesi",oyuncuListesi)
                        finish()
                        startActivity(intent)

                    }else
                    {
                        Toast.makeText(this,"Eleme Seçeneğinde (4-8-16-32) Oyuncu Olmalı!",Toast.LENGTH_LONG).show()
                    }
                } else if(toView.radioButtonCift.isChecked){
                   if (oyuncuListesi.size<=32 && oyuncuListesi.size>=3){
                        TabloDao().tabloEkle(vt, turnuvaAdi,currentDate)
                        val sonTabloid = TabloDao().sonTablo_id(vt)
                        for(i in oyuncuListesi){
                            PuanCetveliDao().oyuncuEkle(vt,i,sonTabloid)
                        }
                        maclarListesi= ArrayList()
                        turnuvaOlusturViewModel.eslestirmeCift(oyuncuListesi)
                        maclarListesi=turnuvaOlusturViewModel.maclar
                        MaclarDao().macEkle(vt,maclarListesi,sonTabloid)

                        val intent = Intent(this@TurnuvaOlusturActivity, PuanCetveliActivity::class.java)
                        intent.putExtra("sonTabloid", sonTabloid)
                        finish()
                        startActivity(intent)

                    }else{
                        Toast.makeText(this,"Lig seçeneklerinde en az 3 en fazla 32 sayıda oyuncu olmalıdır.",Toast.LENGTH_LONG).show()
                    }

                }
            }else{

                Toast.makeText(this,"Lütfen Aynı İsimli Birden Çok Kayıt Girmeyiniz",Toast.LENGTH_LONG).show()
                }
            }
        }

        toView.imgBtngeri.setOnClickListener {

            startActivity(Intent(this@TurnuvaOlusturActivity, MainActivity::class.java))
        }
    }
}
