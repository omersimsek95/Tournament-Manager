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
import com.example.tournamentmanager_turnuvaoluturma.model.Tablo
import com.example.tournamentmanager_turnuvaoluturma.view.PuanCetveliActivity

class TurnuvalarLigAdapter(private val mContext: Context, private val tablolar:ArrayList<Tablo>)
    : RecyclerView.Adapter<TurnuvalarLigAdapter.turnuvalarLigTasarimTutucu>() {


    inner class turnuvalarLigTasarimTutucu(tasarim: View) : RecyclerView.ViewHolder(tasarim) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): turnuvalarLigTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.eski_turnuvalar_lig_card, parent, false)

        return turnuvalarLigTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return tablolar.size
    }

    override fun onBindViewHolder(holder: turnuvalarLigTasarimTutucu, position: Int) {

        holder.txtTurnuvaNo.text="${(tablolar.size-position)}."
        holder.txtTurnuvaADI.text=tablolar[position].tablo_isim
        holder.txtTurnuvaTarih.text=tablolar.get(position).tarih
            holder.cons.setOnClickListener {

                val sonTabloid=tablolar.get(position).tablo_id
                val intent=Intent(mContext, PuanCetveliActivity::class.java)
                intent.putExtra("sonTabloid",sonTabloid)

                mContext.startActivity(intent)


            }


    }
}



