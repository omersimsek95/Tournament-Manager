package com.example.tournamentmanager_turnuvaoluturma.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.tournamentmanager_turnuvaoluturma.model.Tablo
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi

class TabloDao {

    fun tabloEkle(vt: VeriTabaniYardimcisi, tablo_isim:String, tarih:String){

        val db=vt.writableDatabase
        val values=ContentValues()

        values.put("tablo_isim",tablo_isim)
        values.put("tarih",tarih)

        db.insertOrThrow("Tablo",null,values)
        db.close()
    }
    @SuppressLint("Range")
    fun sonTablo_id(vt: VeriTabaniYardimcisi): Int{


        var tabloids=ArrayList<Int>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT tablo_id FROM Tablo ORDER BY tablo_id DESC LIMIT 1",null)
        while (c.moveToNext()) {
          var tabloid = c.getInt(c.getColumnIndex("tablo_id"))
            tabloids.add(tabloid)

        }
        db.close()

        return tabloids[0]

        }
    @SuppressLint("Range")
    fun tabloAd(vt: VeriTabaniYardimcisi, tablo_id:Int): String{

        val db=vt.writableDatabase
        val tabloAdlari=ArrayList<String>()
        val c=db.rawQuery("SELECT tablo_isim FROM Tablo WHERE tablo_id=$tablo_id ",null)
        while (c.moveToNext()) {
            var tabloAd = c.getString(c.getColumnIndex("tablo_isim"))
            tabloAdlari.add(tabloAd)
        }

        return tabloAdlari[0]

    }
    @SuppressLint("Range")
    fun tabloGetir(vt: VeriTabaniYardimcisi): ArrayList<Tablo>{
        val tablolar=ArrayList<Tablo>()
        val db=vt.writableDatabase
        val c=db.rawQuery("SELECT * FROM Tablo ORDER BY tablo_id DESC",null)
        while (c.moveToNext()) {
            var tablo = Tablo(c.getInt(c.getColumnIndex("tablo_id")),
                                c.getString(c.getColumnIndex("tarih")),
                                c.getString(c.getColumnIndex("tablo_isim")))
           tablolar.add(tablo)
        }

        return tablolar

    }
    fun tabloSil(vt: VeriTabaniYardimcisi, sonTabloid:Int){
        val db=vt.writableDatabase
        db.delete("Tablo","tablo_id=?", arrayOf(sonTabloid.toString()))
        db.delete("PuanCetveli","tablo_id=?", arrayOf(sonTabloid.toString()))
        db.delete("Maclar","tablo_id=?", arrayOf(sonTabloid.toString()))


    }

}
