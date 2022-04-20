package com.example.tournamentmanager_turnuvaoluturma.model


data class Maclar(var takim1_isim:String,
                  var takim2_isim:String,
                  var hafta:Int,
                  var takim1_skor:Int?,
                  var takim2_skor:Int?,
                  var tablo_id:Int,
                  var mac_id:Int,
                  var eleme_id:Int)
