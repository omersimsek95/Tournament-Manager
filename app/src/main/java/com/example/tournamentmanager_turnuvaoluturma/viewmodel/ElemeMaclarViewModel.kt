package com.example.tournamentmanager_turnuvaoluturma.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tournamentmanager_turnuvaoluturma.model.MaclarClass
import java.util.ArrayList

class ElemeMaclarViewModel : ViewModel() {
    var maclarList=ArrayList<MaclarClass>(arrayListOf())

    fun eslestirmeEleme(oyuncuListesi: ArrayList<String>, hafta:Int)  {
        var newList=oyuncuListesi
        for(i in 0..oyuncuListesi.size/2-1){

            var firstIndex=(0..newList.size-1).random()
            var firstTeam=newList[firstIndex]
            newList.removeAt(firstIndex)


            var secondIndex=(0..newList.size-1).random()
            var secondTeam=newList[secondIndex]
            newList.removeAt(secondIndex)

            var maclar= MaclarClass(firstTeam,secondTeam,hafta)
            maclarList.add(maclar)
        }
    }
}