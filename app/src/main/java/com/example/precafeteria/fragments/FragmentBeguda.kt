package com.example.precafeteria.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.precafeteria.R
import com.example.precafeteria.databinding.FragmentBegudaBinding
import com.example.precafeteria.models.Plat
import com.example.precafeteria.models.PlatQuantitat
import com.example.precafeteria.viewModels.MenuViewModel

class FragmentBeguda : Fragment() {

    private lateinit var binding: FragmentBegudaBinding
    private val viewModel: MenuViewModel by activityViewModels<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBegudaBinding.inflate(inflater)

        viewModel.beguda.observe(viewLifecycleOwner) { beguda ->
            binding.preuUnitari.text = beguda.plat.preu.toString() + "€"
        }

        val ad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.getBegudes()
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        binding.platSelector.adapter = ad

        binding.platSelector.onItemSelectedListener = getPlatSelectorOnItemSelectedListener()

        binding.upButton.setOnClickListener {updateItemQuantity(1)}
        binding.downButton.setOnClickListener {updateItemQuantity(-1)}

        binding.nextFragmentButton.setOnClickListener {openNextFragmentFunction()}


        return binding.root
    }

    private fun getPlatSelectorOnItemSelectedListener() : AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) { saveBegudaData() }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateItemQuantity(number: Int) {
        val quantity = binding.inputQuantitat.text.toString().toIntOrNull()
        if (quantity != null) {
            val updatedNumber = quantity + number
            if (updatedNumber in 1..99) {
                binding.inputQuantitat.setText(updatedNumber.toString())
            }
        }
    }

    private fun openNextFragmentFunction() {
        if (binding.platSelector.selectedItem.toString() == "Seleccionar") {
            Toast.makeText(context, "Selecciona una beguda", Toast.LENGTH_SHORT).show()
        } else {
            saveBegudaData()
            viewModel.calcularDescompte()
            findNavController().navigate(R.id.action_fragmentBeguda_to_fragmentTotal)
        }
    }

    private fun saveBegudaData() {
        val quantity = binding.inputQuantitat.text.toString().toIntOrNull()
        if (quantity == null || binding.inputQuantitat.text.toString().toInt() !in 1..99) {
            binding.inputQuantitat.setText("1")
        }

        viewModel.setBeguda(
            PlatQuantitat(
                viewModel.getBegudaByName(binding.platSelector.selectedItem.toString()),
                binding.inputQuantitat.text.toString().toInt()
            )
        )
    }
}