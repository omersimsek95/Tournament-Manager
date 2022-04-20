package com.example.tournamentmanager_turnuvaoluturma.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeMaclarClass
import com.example.tournamentmanager_turnuvaoluturma.model.Maclar
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi

class MaclarDao {

    fun macEkle(vt: VeriTabaniYardimcisi, maclar:ArrayList<MaclarClass>, sonTabloid:Int){

        val db=vt.writableDatabase
        val values= ContentValues()
        for (i in 0..maclar.size-1){
            values.put("takim1_isim",maclar[i].takim1_isim)
            values.put("takim2_isim",maclar[i].takim2_isim)
            values.put("hafta",maclar[i].hafta)
            values.put("tablo_id",sonTabloid)
            values.put("takim1_skor",-1)
            db.insertOrThrow("Maclar",null,values)
        }

        db.close()
    }
    @SuppressLint("Range")
    fun macGetir(vt: VeriTabaniYardimcisi, sonTabloid: Int):ArrayList<Maclar>{

        val oyuncularListe=ArrayList<Maclar>()

        val db=vt.writableDatabase
        val c=db.rawQuery("Select * FROM Maclar WHERE tablo_id=${sonTabloid}" ,null)

        while (c.moveToNext()){
            val kelime= Maclar(c.getString(c.getColumnIndex("takim1_isim")),
                c.getString(c.getColumnIndex("takim2_isim")),
                c.getInt(c.getColumnIndex("hafta")),
                c.getInt(c.getColumnIndex("takim1_skor")),
                c.getInt(c.getColumnIndex("takim2_skor")),
                c.getInt(c.getColumnIndex("tablo_id")),
                c.getInt(c.getColumnIndex("mac_id")),
                c.getInt(c.getColumnIndex("eleme_id")))


            oyuncularListe.add(kelime)
        }
        db.close()
        return oyuncularListe
    }
    fun macGuncelle(vt: VeriTabaniYardimcisi, takim1_isim:String, takim2_isim:String, takim1_skor:Int, takim2_skor:Int, sonTabloid: Int){

        val db=vt.writableDatabase
        val values= ContentValues()
            values.put("takim1_skor",takim1_skor)
            values.put("takim2_skor",takim2_skor)


            db.update("Maclar",values,"takim1_isim=? and takim2_isim=? and tablo_id=?", arrayOf(takim1_isim,takim2_isim,sonTabloid.toString()))

        db.close()

    }
    fun elemeMacEkle(vt: VeriTabaniYardimcisi, maclar:ArrayList<MaclarClass>, sonElemeid:Int){

        val db=vt.writableDatabase
        val values= ContentValues()


                var count:Int?
               for (i in maclar){
                   val c=db.rawQuery("SELECT *  FROM Maclar WHERE takim1_isim='${i.takim1_isim}' and takim2_isim='${i.takim2_isim}' and eleme_id=${sonElemeid}",null)
                   val d=db.rawQuery("SELECT *  FROM Maclar WHERE takim1_isim='${i.takim2_isim}' and takim2_isim='${i.takim1_isim}' and eleme_id=${sonElemeid}",null)

                   count=c.count+d.count
                   if (count==0){

                       values.put("takim1_isim",i.takim1_isim)
                       values.put("takim2_isim",i.takim2_isim)
                       values.put("hafta",i.hafta)
                       values.put("eleme_id",sonElemeid)
                       values.put("takim1_skor",-1)
                       db.insertOrThrow("Maclar",null,values)
            }
               }

        db.close()
    }
    @SuppressLint("Range")
    fun elemeMacGetir(vt: VeriTabaniYardimcisi, sonElemeid:Int, hafta:Int):ArrayList<ElemeMaclarClass>{

        val oyuncularListe=ArrayList<ElemeMaclarClass>()

        val db=vt.writableDatabase
        val c=db.rawQuery("Select DISTINCT takim1_isim,takim2_isim,hafta,takim1_skor,takim2_skor,eleme_id FROM Maclar WHERE eleme_id=${sonElemeid} and hafta=$hafta" ,null)

        while (c.moveToNext()){
            val kelime= ElemeMaclarClass(c.getString(c.getColumnIndex("takim1_isim")),
                    c.getString(c.getColumnIndex("takim2_isim")),
                    c.getInt(c.getColumnIndex("hafta")),
                    c.getInt(c.getColumnIndex("takim1_skor")),
                    c.getInt(c.getColumnIndex("takim2_skor")),
                    c.getInt(c.getColumnIndex("eleme_id")))


            oyuncularListe.add(kelime)
        }
        db.close()
        return oyuncularListe
    }
    fun elemeMacGuncelle(vt: VeriTabaniYardimcisi, takim1_isim:String, takim2_isim:String, takim1_skor:Int, takim2_skor:Int, sonElemeid: Int, hafta:Int){

        val db=vt.writableDatabase
        val values= ContentValues()
        values.put("takim1_skor",takim1_skor)
        values.put("takim2_skor",takim2_skor)


        db.update("Maclar",values,"takim1_isim=? and takim2_isim=? and eleme_id=? and hafta=?", arrayOf(takim1_isim,takim2_isim,sonElemeid.toString(),hafta.toString()))

        db.close()

    }
    @SuppressLint("Range")
    fun sonHaftaGetir(vt: VeriTabaniYardimcisi, sonElemeid: Int): Int {


        val elemeids = ArrayList<Int>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT hafta FROM Maclar WHERE eleme_id=$sonElemeid ORDER BY hafta DESC LIMIT 1", null)
        while (c.moveToNext()) {
            val elemeid = c.getInt(c.getColumnIndex("hafta"))
            elemeids.add(elemeid)

        }
        db.close()

        return elemeids[0]

    }


}