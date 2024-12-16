package com.example.precafeteria

import com.example.precafeteria.models.Plat

class PlatsProvider {
    companion object {
        private val plats1: List<Plat> = listOf(
            Plat("Seleccionar", 0.0),
            Plat("Paella", 10.0),
            Plat("Macarrons", 8.0),
            Plat("Croquetas", 5.0),
            Plat("Sopa", 9.0),
        )

        private val begudes: List<Plat> = listOf(
            Plat("Seleccionar", 0.0),
            Plat("Aigua", 2.0),
            Plat("Fanta", 3.0),
            Plat("Coca Cola", 4.0)
        )

        fun getPlats1(): List<Plat> {
            return plats1
        }

        fun getBegudes(): List<Plat> {
            return begudes
        }
    }
}