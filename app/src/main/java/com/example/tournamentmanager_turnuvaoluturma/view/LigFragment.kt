package com.example.tournamentmanager_turnuvaoluturma.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.TabloDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.TurnuvalarLigAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.FragmentLigBinding
import com.example.tournamentmanager_turnuvaoluturma.model.Tablo
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi


class LigFragment : Fragment() {
    private lateinit var lfView:FragmentLigBinding
    private lateinit var vt: VeriTabaniYardimcisi
    private lateinit var tablolar:ArrayList<Tablo>
    private lateinit var mContext:Context
    private lateinit var ligFragmentAdapter: TurnuvalarLigAdapter
    private var attachedContext: Context? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        lfView=DataBindingUtil.inflate(inflater,R.layout.fragment_lig,container,false)
        return lfView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       vt= VeriTabaniYardimcisi(requireActivity())


        tablolar= ArrayList()

        tablolar= TabloDao().tabloGetir(vt)

        lfView.rvTurnuvalarLig.layoutManager=LinearLayoutManager(requireActivity())
        lfView.rvTurnuvalarLig.setHasFixedSize(true)
        ligFragmentAdapter= TurnuvalarLigAdapter(requireActivity(),tablolar)
        lfView.rvTurnuvalarLig.adapter=ligFragmentAdapter

    }




}

