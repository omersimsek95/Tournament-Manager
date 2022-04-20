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
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeMaclarClass
import com.example.tournamentmanager_turnuvaoluturma.view.ElemeSonucEklemeActivity

class TurlarAdapter(private val mContext: Context, private val maclarList:ArrayList<ElemeMaclarClass>, private val sonElemeid:Int, private val hafta:Int, private val enBuyukHafta:Int)
    : RecyclerView.Adapter<TurlarAdapter.eleme_cardTasarimTutucu>() {
        private lateinit var kazananTakimlar:ArrayList<String>




    inner class eleme_cardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){

        var txtTakim1: TextView
        var txtTakim2: TextView
        var txtSkor: TextView
        var cons: ConstraintLayout
        var viewAltCizgi:View



        init {
            txtTakim1=tasarim.findViewById(R.id.txtTakim1)
            txtTakim2=tasarim.findViewById(R.id.txtTakim2)
            txtSkor=tasarim.findViewById(R.id.txtSkor)
            cons=tasarim.findViewById(R.id.cons)
            viewAltCizgi=tasarim.findViewById(R.id.viewAltCizgi)





        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eleme_cardTasarimTutucu {
        val tasarim= LayoutInflater.from(mContext).inflate(R.layout.turlar_card,parent,false)



        return eleme_cardTasarimTutucu(tasarim)
    }
    override fun getItemCount(): Int {
        return maclarList.size
    }

    override fun onBindViewHolder(holder: eleme_cardTasarimTutucu, position: Int)  {


           holder.txtTakim1.text=maclarList.get(position).takim1_isim
           holder.txtTakim2.text=maclarList.get(position).takim2_isim
           holder.txtSkor.text="${maclarList.get(position).takim1_skor}-${maclarList.get(position).takim2_skor}"
            if (maclarList.get(position).hafta<enBuyukHafta){
                holder.cons.setEnabled(false)
                holder.txtSkor.setEnabled(false)
            }




        if (maclarList.get(position).takim1_skor==-1){

            holder.txtSkor.text="-"
            holder.cons.setOnClickListener {
                val intent2=Intent(mContext, ElemeSonucEklemeActivity::class.java)
                intent2.putExtra("takim1",holder.txtTakim1.text)
                intent2.putExtra("takim2",holder.txtTakim2.text)
                intent2.putExtra("sonElemeid",sonElemeid)
                intent2.putExtra("takim1_skor",-1)
                intent2.putExtra("hafta",hafta)
                intent2.putExtra("takim2_skor",0)

                mContext.startActivity(intent2)
            }
        }else{

            holder.txtSkor.text="${maclarList.get(position).takim1_skor}-${maclarList.get(position).takim2_skor}"
            holder.cons.setOnClickListener {
                val intent2=Intent(mContext, ElemeSonucEklemeActivity::class.java)
                intent2.putExtra("takim1",holder.txtTakim1.text)
                intent2.putExtra("takim2",holder.txtTakim2.text)
                intent2.putExtra("sonElemeid",sonElemeid)
                intent2.putExtra("hafta",hafta)
                intent2.putExtra("takim1_skor",maclarList.get(position).takim1_skor)
                intent2.putExtra("takim2_skor",maclarList.get(position).takim2_skor)
                mContext.startActivity(intent2)
            }
        }







    }

    }










