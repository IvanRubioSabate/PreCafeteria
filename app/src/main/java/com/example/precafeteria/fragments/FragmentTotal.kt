package com.example.precafeteria.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.precafeteria.databinding.FragmentTotalBinding
import com.example.precafeteria.viewModels.MenuViewModel

class FragmentTotal : Fragment() {

    private lateinit var binding: FragmentTotalBinding
    private val viewModel: MenuViewModel by activityViewModels<MenuViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTotalBinding.inflate(inflater)

        binding.nomPrimerPlat.text = viewModel.plat1.value!!.plat.nom
        binding.preuPrimerPlat.text = viewModel.plat1.value!!.plat.preu.toString()
        binding.quantitatPrimerPlat.text = viewModel.plat1.value!!.quantitat.toString()
        binding.totalPrimerPlat.text = viewModel.getTotalPlat1().toString()

        binding.nomBeguda.text = viewModel.beguda.value!!.plat.nom
        binding.preuBeguda.text = viewModel.beguda.value!!.plat.preu.toString()
        binding.quantitatBeguda.text = viewModel.beguda.value!!.quantitat.toString()
        binding.totalBeguda.text = viewModel.getTotalBeguda().toString()

        binding.totalPreu.text = viewModel.getTotal().toString()
        binding.descompte.text = viewModel.descompte.toString() + "%"
        binding.totalFinal.text = viewModel.getTotalDescomptat().toString()

        return binding.root
    }
}