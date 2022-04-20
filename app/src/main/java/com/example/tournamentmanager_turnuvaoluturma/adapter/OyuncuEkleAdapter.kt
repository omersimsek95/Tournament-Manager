package com.example.tournamentmanager_turnuvaoluturma.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanager_turnuvaoluturma.R

class OyuncuEkleAdapter(private val mContext:Context, private val oyuncuList:ArrayList<String>)
    : RecyclerView.Adapter<OyuncuEkleAdapter.oyuncu_cardTasarimTutucu>() {

     lateinit var  oyuncuAdlari :ArrayList<String>

    inner class oyuncu_cardTasarimTutucu(tasarim:View): RecyclerView.ViewHolder(tasarim){

        var txtOyunciAdi:TextView
        var imgBtnDelete: ImageView
        var textSira: TextView


        init {
            txtOyunciAdi=tasarim.findViewById(R.id.txtOyuncuAdi)
            imgBtnDelete=tasarim.findViewById(R.id.imgBtnDelete)
            textSira=tasarim.findViewById(R.id.textSira)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): oyuncu_cardTasarimTutucu {
        val tasarim=LayoutInflater.from(mContext).inflate(R.layout.oyuncu_cardtasarim,parent,false)

        return oyuncu_cardTasarimTutucu(tasarim)
    }
    override fun getItemCount(): Int {
        return oyuncuList.size
    }

    override fun onBindViewHolder(holder: oyuncu_cardTasarimTutucu, position: Int)  {

            holder.txtOyunciAdi.text=oyuncuList[position]
            holder.textSira.text="${position+1}."


             holder.imgBtnDelete.setOnClickListener{
            oyuncuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, oyuncuList.size)
            holder.itemView.visibility = View.GONE

          }



    }
    fun geriDondur() : ArrayList<String> {

        return oyuncuList

    }
}




