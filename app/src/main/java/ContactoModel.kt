package com.example.listacontactos

data class ContactoModel(
    val nombre: String,
    val telefono: String,
    val email: String,
    val imagenId: Int // Recurso de imagen
)
