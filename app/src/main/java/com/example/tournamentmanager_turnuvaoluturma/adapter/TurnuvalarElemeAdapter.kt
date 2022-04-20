package com.example.tournamentmanager_turnuvaoluturma.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanager_turnuvaoluturma.R
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeTablo
import com.example.tournamentmanager_turnuvaoluturma.view.ElemeMaclarActivity

class TurnuvalarElemeAdapter(private val mContext: Context, private val tablolar:ArrayList<ElemeTablo>)
    : RecyclerView.Adapter<TurnuvalarElemeAdapter.turnuvalarElemeTasarimTutucu>() {


    inner class turnuvalarElemeTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {
        var txtTurnuvaNo: TextView
        var txtTurnuvaADI: TextView
        var txtTurnuvaTarih: TextView
        var cons: ConstraintLayout



        init {
            txtTurnuvaNo = tasarim.findViewById(R.id.txtTurnuvaNo)
            txtTurnuvaADI = tasarim.findViewById(R.id.txtTurnuvaADI)
            txtTurnuvaTarih = tasarim.findViewById(R.id.txtTurnuvaTarih)
            cons = tasarim.findViewById(R.id.cons)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): turnuvalarElemeTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.eski_turnuvalar_eleme_card, parent, false)

        return turnuvalarElemeTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return tablolar.size
    }

    override fun onBindViewHolder(holder: turnuvalarElemeTasarimTutucu, position: Int) {

        holder.txtTurnuvaNo.text="${(tablolar.size-position)}."
        holder.txtTurnuvaADI.text=tablolar[position].tablo_isim
        holder.txtTurnuvaTarih.text=tablolar.get(position).tarih
            holder.cons.setOnClickListener {
                val sonElemeid=tablolar.get(position).eleme_id
                val intent=Intent(mContext, ElemeMaclarActivity::class.java)
                intent.putExtra("sonElemeid",sonElemeid)
                mContext.startActivity(intent)


            }


    }
}



