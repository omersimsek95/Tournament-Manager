package com.example.tournamentmanager_turnuvaoluturma.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanager_turnuvaoluturma.R
import com.example.tournamentmanager_turnuvaoluturma.databinding.PuanCetveliCardBinding
import com.example.tournamentmanager_turnuvaoluturma.model.PuanCetveli

class PuanCetveliAdapter(private val mContext:Context, private val oyuncuList:ArrayList<PuanCetveli>)
    : RecyclerView.Adapter<PuanCetveliAdapter.puanCetveliCardTasarimTutucu>() {


    inner class puanCetveliCardTasarimTutucu( var view:PuanCetveliCardBinding): RecyclerView.ViewHolder(view.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): puanCetveliCardTasarimTutucu {
        val inflater=LayoutInflater.from(mContext)
        val view=DataBindingUtil.inflate<PuanCetveliCardBinding>(inflater,R.layout.puan_cetveli_card,parent,false)

        return puanCetveliCardTasarimTutucu(view)
    }
    override fun getItemCount(): Int {
        return oyuncuList.size
    }

    override fun onBindViewHolder(holder: puanCetveliCardTasarimTutucu, position: Int)  {

        holder.view.puanCetveli= oyuncuList[position]
    }
}




