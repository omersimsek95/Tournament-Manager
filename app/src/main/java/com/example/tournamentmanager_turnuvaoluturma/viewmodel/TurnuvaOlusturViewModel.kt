package com.example.tournamentmanager_turnuvaoluturma.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass

class TurnuvaOlusturViewModel : ViewModel() {

    var maclar=ArrayList<MaclarClass>(arrayListOf())
    private lateinit var oyuncuAdlari:ArrayList<String>


    fun eslestirmeTek(oyuncuAdlariList: ArrayList<String>){
        oyuncuAdlari=oyuncuAdlariList
        if (oyuncuAdlari.size%2==0){

            val roundCount=oyuncuAdlari.size-1
            val matchPerRound=oyuncuAdlari.size/2

            for (i in 0..roundCount-1){
                for (j in 0..matchPerRound-1){
                    val firstTeam=oyuncuAdlari[j]
                    val secondTeam=oyuncuAdlari[oyuncuAdlari.size-j-1]

                    val maclarObject= MaclarClass(firstTeam,secondTeam,(i+1))
                    maclar.add(maclarObject)
                }
                val newList= ArrayList<String>()
                newList.add(oyuncuAdlari[0])
                newList.add(oyuncuAdlari.get(oyuncuAdlari.size-1))
                for(k in 1..oyuncuAdlari.size-2){
                    newList.add(oyuncuAdlari.get(k))
                }
                oyuncuAdlari=newList
            }
        }
        else
        {
            val newOyuncuAdlari=ArrayList<String>()
            for (i in 0..oyuncuAdlari.size/2){
                newOyuncuAdlari.add(oyuncuAdlari[i])
            }
            newOyuncuAdlari.add("(bay Geçti)")
            for (i in oyuncuAdlari.size/2+1..oyuncuAdlari.size-1){
                newOyuncuAdlari.add(oyuncuAdlari[i])
            }
            oyuncuAdlari=newOyuncuAdlari

            val roundCount=oyuncuAdlari.size-1
            val matchPerRound=oyuncuAdlari.size/2

            for (i in 0..roundCount-1){
                for (j in 0..matchPerRound-1){
                    val firstTeam=oyuncuAdlari[j]
                    val secondTeam=oyuncuAdlari[oyuncuAdlari.size-j-1]

                    val maclarObject= MaclarClass(firstTeam,secondTeam,(i+1))
                    maclar.add(maclarObject)
                }

                val newList= ArrayList<String>()
                newList.add(oyuncuAdlari[0])
                newList.add(oyuncuAdlari.get(oyuncuAdlari.size-1))
                for(k in 1..oyuncuAdlari.size-2){
                    newList.add(oyuncuAdlari.get(k))
                }
                oyuncuAdlari=newList
            }
        }
    }
    fun eslestirmeCift(oyuncuAdlariList: ArrayList<String>) {


        oyuncuAdlari=oyuncuAdlariList
        if (oyuncuAdlari.size%2==0) {

            maclar = ArrayList()

            val roundCount = oyuncuAdlari.size - 1
            val matchPerRound = oyuncuAdlari.size / 2

            for (i in 0..roundCount - 1) {

                for (j in 0..matchPerRound - 1) {
                    val firstTeam = oyuncuAdlari[j]
                    val secondTeam = oyuncuAdlari[oyuncuAdlari.size - j - 1]

                    val maclarObject = MaclarClass(firstTeam, secondTeam, (i + 1))
                    maclar.add(maclarObject)

                }
                val newList = ArrayList<String>()
                newList.add(oyuncuAdlari[0])
                newList.add(oyuncuAdlari.get(oyuncuAdlari.size - 1))
                for (k in 1..oyuncuAdlari.size - 2) {
                    newList.add(oyuncuAdlari.get(k))
                }
                oyuncuAdlari = newList

            }
            val k = maclar.size
            for (l in 0..k - 1) {

                val maclarCifObject = MaclarClass(maclar[l].takim2_isim, maclar.get(l).takim1_isim, (maclar.get(l).hafta!! + roundCount))
                maclar.add(maclarCifObject)
            }
        }
        else{

            maclar= ArrayList()
            val newOyuncuAdlari=ArrayList<String>()
            for (i in 0..oyuncuAdlari.size/2){
                newOyuncuAdlari.add(oyuncuAdlari[i])
            }
            newOyuncuAdlari.add("(bay Geçti)")
            for (i in oyuncuAdlari.size/2+1..oyuncuAdlari.size-1){
                newOyuncuAdlari.add(oyuncuAdlari[i])
            }
            oyuncuAdlari=newOyuncuAdlari

            val roundCount=oyuncuAdlari.size-1
            val matchPerRound=oyuncuAdlari.size/2

            for (i in 0..roundCount-1){
                for (j in 0..matchPerRound-1){
                    val firstTeam=oyuncuAdlari[j]
                    val secondTeam=oyuncuAdlari[oyuncuAdlari.size-j-1]

                    val maclarObject= MaclarClass(firstTeam,secondTeam,(i+1))
                    maclar.add(maclarObject)
                }
                val newList= ArrayList<String>()
                newList.add(oyuncuAdlari[0])
                newList.add(oyuncuAdlari.get(oyuncuAdlari.size-1))
                for(k in 1..oyuncuAdlari.size-2){
                    newList.add(oyuncuAdlari.get(k))
                }
                oyuncuAdlari=newList

            }

            val k=maclar.size
            for (l in 0..k-1){

                val maclarCifObject= MaclarClass(maclar[l].takim2_isim,maclar.get(l).takim1_isim,(maclar.get(l).hafta!!+roundCount))
                maclar.add(maclarCifObject)
            }
        }

    }
    fun eslestirmeEleme(oyuncuListesi: java.util.ArrayList<String>, hafta:Int) {

        val newList=oyuncuListesi
        for(i in 0..oyuncuListesi.size/2-1){

            val firstIndex=(0..newList.size-1).random()
            val firstTeam=newList[firstIndex]
            newList.removeAt(firstIndex)


            val secondIndex=(0..newList.size-1).random()
            val secondTeam=newList[secondIndex]
            newList.removeAt(secondIndex)

            val mac= MaclarClass(firstTeam,secondTeam,hafta)
            maclar.add(mac)
        }

    }
}