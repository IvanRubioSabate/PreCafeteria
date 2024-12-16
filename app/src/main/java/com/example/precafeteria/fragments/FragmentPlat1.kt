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
import com.example.precafeteria.viewModels.MenuViewModel
import com.example.precafeteria.databinding.FragmentPlat1Binding
import com.example.precafeteria.models.Plat
import com.example.precafeteria.models.PlatQuantitat

class FragmentPlat1 : Fragment() {

    private lateinit var binding: FragmentPlat1Binding
    private val viewModel: MenuViewModel by activityViewModels<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlat1Binding.inflate(inflater)

        viewModel.plat1.observe(viewLifecycleOwner) { plat1 ->
            binding.preuUnitari.text = plat1.plat.preu.toString() + "€"
        }

        val ad = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.getPlats1()
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
            ) { savePlatData() }
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
            Toast.makeText(context, "Selecciona un plat", Toast.LENGTH_SHORT).show()
        } else {
            savePlatData()
            findNavController().navigate(R.id.action_plat1_to_fragmentBeguda)
        }
    }

    private fun savePlatData() {
        val quantity = binding.inputQuantitat.text.toString().toIntOrNull()
        if (quantity == null || binding.inputQuantitat.text.toString().toInt() !in 1..99) {
            binding.inputQuantitat.setText("1")
        }

        viewModel.setPlat1(
            PlatQuantitat(
                viewModel.getPlatByName(binding.platSelector.selectedItem.toString()),
                binding.inputQuantitat.text.toString().toInt()
            )
        )
    }
}