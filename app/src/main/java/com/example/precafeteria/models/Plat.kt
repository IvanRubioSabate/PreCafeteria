package com.example.precafeteria.models

data class Plat(var nom: String, var preu: Double)
{
    override fun toString(): String {
        return nom
    }
}
