package com.example.tournamentmanager_turnuvaoluturma.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanager_turnuvaoluturma.*
import com.example.tournamentmanager_turnuvaoluturma.dao.ElemeDao
import com.example.tournamentmanager_turnuvaoluturma.adapter.TurnuvalarElemeAdapter
import com.example.tournamentmanager_turnuvaoluturma.databinding.FragmentElemeBinding
import com.example.tournamentmanager_turnuvaoluturma.model.ElemeTablo
import com.example.tournamentmanager_turnuvaoluturma.service.VeriTabaniYardimcisi



class ElemeFragment : Fragment() {
    private lateinit var efView:FragmentElemeBinding
    private lateinit var vt: VeriTabaniYardimcisi
    private lateinit var tablolar:ArrayList<ElemeTablo>
    private lateinit var elemeFragmentAdapter: TurnuvalarElemeAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        efView=DataBindingUtil.inflate(inflater,R.layout.fragment_eleme,container,false)

        return efView.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vt= VeriTabaniYardimcisi(requireActivity())
        tablolar= ArrayList()
        tablolar= ElemeDao().elemeTabloGetir(vt)

        efView.rvTurnuvalarEleme.layoutManager= LinearLayoutManager(requireActivity())
        efView.rvTurnuvalarEleme.setHasFixedSize(true)
        elemeFragmentAdapter= TurnuvalarElemeAdapter(requireActivity(),tablolar)
        efView.rvTurnuvalarEleme.adapter=elemeFragmentAdapter
    }



}