package com.example.tournamentmanager_turnuvaoluturma.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import com.example.tournamentmanager_turnuvaoluturma.model.Maclar
import com.example.tournamentmanager_turnuvaoluturma.model.PuanCetveli
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi

class PuanCetveliDao {

    @SuppressLint("Range")
    fun tumOyuncular(vt: VeriTabaniYardimcisi, tablo_id: Int): ArrayList<PuanCetveli>{

        val oyuncularListe=ArrayList<PuanCetveli>()

        val db=vt.writableDatabase
        val c=db.rawQuery("Select * FROM PuanCetveli WHERE tablo_id=${tablo_id} ORDER BY puan DESC, av DESC,ag DESC,g DESC" ,null)

        while (c.moveToNext()){
            val kelime= PuanCetveli(c.getInt(c.getColumnIndex("takim_id")),
                c.getInt(c.getColumnIndex("puan")),
                c.getInt(c.getColumnIndex("om")),
                c.getInt(c.getColumnIndex("g")),
                c.getInt(c.getColumnIndex("m")),
                c.getInt(c.getColumnIndex("b")),
                c.getInt(c.getColumnIndex("ag")),
                c.getInt(c.getColumnIndex("yg")),
                c.getInt(c.getColumnIndex("av")),
                c.getString(c.getColumnIndex("takim_ismi")),
                c.getInt(c.getColumnIndex("tablo_id")))

            oyuncularListe.add(kelime)
        }
        db.close()
        return oyuncularListe
    }
    fun oyuncuEkle(vt: VeriTabaniYardimcisi, takim_ismi:String, tablo_id:Int){

        val db=vt.writableDatabase
        val values= ContentValues()

        values.put("takim_ismi",takim_ismi)
        values.put("tablo_id",tablo_id)
        values.put("puan",0)
        values.put("om",0)
        values.put("g",0)
        values.put("m",0)
        values.put("b",0)
        values.put("ag",0)
        values.put("yg",0)
        values.put("av",0)
        db.insertOrThrow("PuanCetveli",null,values)
        db.close()
    }
    @SuppressLint("Range")
    fun oyuncuGuncelle(vt: VeriTabaniYardimcisi, tablo_id:Int){


        val db=vt.writableDatabase
        val oyuncularListe=ArrayList<Maclar>()
        db.execSQL("UPDATE PuanCetveli SET puan=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET om=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET g=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET b=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET m=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET ag=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET yg=0 WHERE tablo_id=$tablo_id")
        db.execSQL("UPDATE PuanCetveli SET av=0 WHERE tablo_id=$tablo_id")

        val c=db.rawQuery("Select * FROM Maclar WHERE tablo_id=${tablo_id}" ,null)

        while (c.moveToNext()) {

            val kelime = Maclar(c.getString(c.getColumnIndex("takim1_isim")),
                    c.getString(c.getColumnIndex("takim2_isim")),
                    c.getInt(c.getColumnIndex("hafta")),
                    c.getInt(c.getColumnIndex("takim1_skor")),
                    c.getInt(c.getColumnIndex("takim2_skor")),
                    c.getInt(c.getColumnIndex("tablo_id")),
                    c.getInt(c.getColumnIndex("mac_id")),
                c.getInt(c.getColumnIndex("eleme_id")))
            oyuncularListe.add(kelime)

        }
        for (i in 0..oyuncularListe.size-1) {
            val takim1_isim: String = oyuncularListe[i].takim1_isim
            val takim2_isim: String = oyuncularListe[i].takim2_isim
            val takim1_skor: Int? = oyuncularListe[i].takim1_skor
            val takim2_skor: Int? = oyuncularListe[i].takim2_skor


                if (takim1_skor ==-1 ) {

                } else {
                    if (takim1_skor!! > takim2_skor!!) {db.execSQL("UPDATE PuanCetveli SET puan=puan+3 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET g=g+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET m=m+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim1_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim2_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim2_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim1_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        //db.execSQL("UPDATE PuanCetveli SET av=av+${takim1_skor - takim2_skor} WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                       // db.execSQL("UPDATE PuanCetveli SET av=av+${takim2_skor - takim1_skor} WHERE takim_ismi='${takim2_skor}' and tablo_id=$tablo_id")

                    } else if (takim2_skor!! > takim1_skor!!) {
                        db.execSQL("UPDATE PuanCetveli SET puan=puan+3 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET g=g+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET m=m+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim1_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim2_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim2_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim1_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                       // db.execSQL("UPDATE PuanCetveli SET av=av+${takim2_skor - takim1_skor} WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        //db.execSQL("UPDATE PuanCetveli SET av=av+${takim1_skor - takim2_skor} WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                    } else {
                        db.execSQL("UPDATE PuanCetveli SET puan=puan+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET puan=puan+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET om=om+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET b=b+1 WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET b=b+1 WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim1_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim2_skor WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET ag=ag+$takim2_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        db.execSQL("UPDATE PuanCetveli SET yg=yg+$takim1_skor WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        //db.execSQL("UPDATE PuanCetveli SET av=av+${takim2_skor - takim1_skor} WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
                        //db.execSQL("UPDATE PuanCetveli SET av=av+${takim1_skor - takim2_skor} WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
                    }

                }
            db.execSQL("UPDATE PuanCetveli SET av=ag-yg WHERE takim_ismi='${takim2_isim}' and tablo_id=$tablo_id")
            db.execSQL("UPDATE PuanCetveli SET av=ag-yg WHERE takim_ismi='${takim1_isim}' and tablo_id=$tablo_id")
        }


        db.close()


    }
    @SuppressLint("Range")
    fun tumOyuncuAdlari(vt: VeriTabaniYardimcisi): ArrayList<String>{

        val oyuncularListe=ArrayList<String>()

        val db=vt.writableDatabase
        val c=db.rawQuery("Select DISTINCT takim_ismi FROM PuanCetveli " ,null)

        while (c.moveToNext()){
            val oyuncuAdi=c.getString(c.getColumnIndex("takim_ismi"))

            oyuncularListe.add(oyuncuAdi)
        }
        db.close()
        return oyuncularListe
    }


}

