package com.example.tournamentmanager_turnuvaoluturma.service

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class VeriTabaniYardimcisi(context: Context): SQLiteOpenHelper(context,"turnuvamanager.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"PuanCetveli\" (\n" +
                "\t\"takim_id\"\tINTEGER,\n" +
                "\t\"puan\"\tINTEGER,\n" +
                "\t\"om\"\tINTEGER,\n" +
                "\t\"g\"\tINTEGER,\n" +
                "\t\"m\"\tINTEGER,\n" +
                "\t\"b\"\tINTEGER,\n" +
                "\t\"ag\"\tINTEGER,\n" +
                "\t\"yg\"\tINTEGER,\n" +
                "\t\"av\"\tINTEGER,\n" +
                "\t\"takim_ismi\"\tTEXT,\n" +
                "\t\"tablo_id\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"takim_id\" AUTOINCREMENT)\n" +
                ")")
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"Tablo\" (\n" +
                "\t\"tablo_id\"\tINTEGER NOT NULL,\n" +
                "\t\"tarih\"\tTEXT NOT NULL,\n" +
                "\t\"tablo_isim\"\tTEXT NOT NULL,\n" +
                "\tPRIMARY KEY(\"tablo_id\" AUTOINCREMENT)\n" +
                ")"
        )
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"Maclar\" (\n" +
                "\t\"takim1_isim\"\tTEXT,\n" +
                "\t\"takim2_isim\"\tTEXT,\n" +
                "\t\"takim1_skor\"\tINTEGER,\n" +
                "\t\"takim2_skor\"\tINTEGER,\n" +
                "\t\"tablo_id\"\tINTEGER,\n" +
                "\t\"mac_id\"\tINTEGER,\n" +
                "\t\"hafta\"\tINTEGER,\n" +
                "\t\"eleme_id\"\tINTEGER,\n" +
                "\t\"tur\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"mac_id\" AUTOINCREMENT)\n" +
                ")")
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"ElemeTablo\" (\n" +
                "\t\"eleme_id\"\tINTEGER,\n" +
                "\t\"tablo_isim\"\tINTEGER,\n" +
                "\t\"tarih\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"eleme_id\" AUTOINCREMENT)\n" +
                ")")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS PuanCetveli")
        db?.execSQL("DROP TABLE IF EXISTS Tablo")
        db?.execSQL("DROP TABLE IF EXISTS Maclar")
        db?.execSQL("DROP TABLE IF EXISTS ElemeTablo")
        onCreate(db)
    }
}