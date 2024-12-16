package com.example.precafeteria.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.precafeteria.PlatsProvider
import com.example.precafeteria.models.Plat
import com.example.precafeteria.models.PlatQuantitat

class MenuViewModel: ViewModel() {

    private var _plat1 = MutableLiveData<PlatQuantitat>()
    val plat1: LiveData<PlatQuantitat> = _plat1

    private var _beguda = MutableLiveData<PlatQuantitat>()
    val beguda: LiveData<PlatQuantitat> = _beguda

    private var _descompte: Int = 0
    val descompte : Int
        get() = _descompte

    fun getPlats1(): List<Plat> {
        return PlatsProvider.getPlats1()
    }

    fun getBegudes(): List<Plat> {
        return PlatsProvider.getBegudes()
    }

    fun getPlatByName(platName: String): Plat {
        return getPlats1().find { it.nom == platName }!!
    }

    fun getBegudaByName(begudaName: String): Plat {
        return getBegudes().find { it.nom == begudaName }!!
    }

    fun getTotalPlat1(): Number {
        return _plat1.value!!.plat.preu * _plat1.value!!.quantitat
    }

    fun getTotalBeguda(): Number {
        return _beguda.value!!.plat.preu * _beguda.value!!.quantitat
    }

    fun getTotal(): Number {
        var total : Double = 0.0

        total += plat1.value!!.plat.preu * plat1.value!!.quantitat
        total += beguda.value!!.plat.preu * beguda.value!!.quantitat

        return total
    }

    fun getTotalDescomptat(): Number {
        val total : Double = getTotal().toDouble()

        return total - (total * (_descompte.toDouble() / 100.0))
    }

    fun setPlat1(plat: PlatQuantitat) {
        _plat1.value = plat
    }

    fun setBeguda(beguda: PlatQuantitat) {
        _beguda.value = beguda
    }

    fun calcularDescompte() {
        var descompte = 0;
        val llistaQuantitats: List<Int> = listOf(
            _plat1.value!!.quantitat,
            _beguda.value!!.quantitat
        )

        llistaQuantitats.forEach { quantitat ->
            if (quantitat >= 10) { descompte += 10}
        }

        _descompte = descompte
    }
}