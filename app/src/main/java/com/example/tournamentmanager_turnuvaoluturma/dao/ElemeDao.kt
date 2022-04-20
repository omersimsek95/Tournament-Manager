package com.example.tournamentmanager_turnuvaoluturma.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeTablo
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi

class ElemeDao {
    fun elemeTabloEkle(vt: VeriTabaniYardimcisi, tablo_isim: String, tarih: String) {

        val db = vt.writableDatabase
        val values = ContentValues()

        values.put("tablo_isim", tablo_isim)
        values.put("tarih", tarih)

        db.insertOrThrow("ElemeTablo", null, values)
        db.close()

    }

    @SuppressLint("Range")
    fun sonElemeidGetir(vt: VeriTabaniYardimcisi): Int {


        var elemeids = ArrayList<Int>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT eleme_id FROM ElemeTablo ORDER BY eleme_id DESC LIMIT 1", null)
        while (c.moveToNext()) {
            var elemeid = c.getInt(c.getColumnIndex("eleme_id"))
            elemeids.add(elemeid)

        }
        db.close()

        return elemeids[0]

    }

    @SuppressLint("Range")
    fun elemeTabloAd(vt: VeriTabaniYardimcisi, eleme_id: Int): String {

        val db = vt.writableDatabase
        val tabloAdlari = ArrayList<String>()
        val c = db.rawQuery("SELECT tablo_isim FROM ElemeTablo WHERE eleme_id=$eleme_id ", null)
        while (c.moveToNext()) {
            var tabloAd = c.getString(c.getColumnIndex("tablo_isim"))
            tabloAdlari.add(tabloAd)
        }

        return tabloAdlari[0]

    }

    fun elemeTabloSil(vt: VeriTabaniYardimcisi, sonElemeid: Int) {
        val db = vt.writableDatabase
        db.delete("ElemeTablo", "eleme_id=?", arrayOf(sonElemeid.toString()))
        db.delete("Maclar", "eleme_id=?", arrayOf(sonElemeid.toString()))


    }

    @SuppressLint("Range")
    fun elemeTabloGetir(vt: VeriTabaniYardimcisi): ArrayList<ElemeTablo> {
        val tablolar = ArrayList<ElemeTablo>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM ElemeTablo ORDER BY eleme_id DESC", null)
        while (c.moveToNext()) {
            var tablo = ElemeTablo(c.getInt(c.getColumnIndex("eleme_id")),
                    c.getString(c.getColumnIndex("tarih")),
                    c.getString(c.getColumnIndex("tablo_isim")))
            tablolar.add(tablo)
        }

        return tablolar


    }

}