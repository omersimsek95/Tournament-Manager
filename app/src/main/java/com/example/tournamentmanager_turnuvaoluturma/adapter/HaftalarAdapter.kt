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
import com.example.tournamentmanager_turnuvaoluturma.model.Maclar
import com.example.tournamentmanager_turnuvaoluturma.view.SonucEklemeActivity

class HaftalarAdapter(private val mContext: Context, private val maclarList:ArrayList<Maclar>, private val sonTabloid:Int, private var matchPerRound:Int)
    : RecyclerView.Adapter<HaftalarAdapter.hafta_cardTasarimTutucu>() {





    inner class hafta_cardTasarimTutucu(tasarim: View): RecyclerView.ViewHolder(tasarim){
        var txtHafta: TextView
        var txtTakim1: TextView
        var txtTakim2: TextView
        var txtSkor: TextView
        var cons: ConstraintLayout
        var viewAltCizgi:View



        init {
            txtHafta=tasarim.findViewById(R.id.txtHafta)
            txtTakim1=tasarim.findViewById(R.id.txtTakim1)
            txtTakim2=tasarim.findViewById(R.id.txtTakim2)
            txtSkor=tasarim.findViewById(R.id.txtSkor)
            cons=tasarim.findViewById(R.id.cons)
            viewAltCizgi=tasarim.findViewById(R.id.viewAltCizgi)





        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hafta_cardTasarimTutucu {
        val tasarim= LayoutInflater.from(mContext).inflate(R.layout.haftalar_card,parent,false)



        return hafta_cardTasarimTutucu(tasarim)
    }
    override fun getItemCount(): Int {
        return maclarList.size
    }

    override fun onBindViewHolder(holder: hafta_cardTasarimTutucu, position: Int)  {


        if (position%matchPerRound==matchPerRound-1){
            holder.viewAltCizgi.visibility=View.VISIBLE


        }
        else{
            holder.viewAltCizgi.visibility=View.INVISIBLE

        }
        if(position%matchPerRound==0){

            holder.txtHafta.visibility=View.VISIBLE
        }else{
            holder.txtHafta.visibility=View.INVISIBLE
        }

           holder.txtHafta.text="${maclarList.get(position).hafta}. HAFTA"
           holder.txtTakim1.text=maclarList.get(position).takim1_isim
           holder.txtTakim2.text=maclarList.get(position).takim2_isim

            if (maclarList[position].takim1_isim.equals("(bay Geçti)") || maclarList[position].takim2_isim.equals("(bay Geçti)")){
                holder.cons.setEnabled(false)


            }

            if (maclarList.get(position).takim1_skor==-1){

                holder.txtSkor.text="-"
                holder.cons.setOnClickListener {
                    val intent2=Intent(mContext, SonucEklemeActivity::class.java)
                    intent2.putExtra("takim1",holder.txtTakim1.text)
                    intent2.putExtra("takim2",holder.txtTakim2.text)
                    intent2.putExtra("sonTabloid",sonTabloid)
                    intent2.putExtra("takim1_skor",-1)
                    intent2.putExtra("takim2_skor",0)

                    mContext.startActivity(intent2)
                }
            }else{
                holder.txtSkor.text="${maclarList.get(position).takim1_skor}-${maclarList.get(position).takim2_skor}"
                holder.cons.setOnClickListener {
                    val intent2=Intent(mContext, SonucEklemeActivity::class.java)
                    intent2.putExtra("takim1",holder.txtTakim1.text)
                    intent2.putExtra("takim2",holder.txtTakim2.text)
                    intent2.putExtra("sonTabloid",sonTabloid)
                    intent2.putExtra("takim1_skor",maclarList.get(position).takim1_skor)
                    intent2.putExtra("takim2_skor",maclarList.get(position).takim2_skor)
                    mContext.startActivity(intent2)
                }
            }



        var i=position/matchPerRound
       if (i>2) {
           i=i%3
       }
        if (i==0){
            holder.cons.backgroundTintList=mContext.getColorStateList(R.color.renk1)}
        if (i==1){
            holder.cons.backgroundTintList=mContext.getColorStateList(R.color.renk2)}
        if (i==2){
            holder.cons.backgroundTintList=mContext.getColorStateList(R.color.renk3)}


    }





        }




