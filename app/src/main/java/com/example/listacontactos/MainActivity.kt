package com.example.listacontactos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var contactos: MutableList<ContactoModel>
    private lateinit var contactosFiltrados: MutableList<ContactoModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewContactos)
        val searchView = findViewById<SearchView>(R.id.searchView)

        // Crear una lista de contactos de ejemplo
        contactos = mutableListOf(
            ContactoModel("Emiliano Moreno", "5532547620", "emiliano.moreno@gmail.com", R.drawable.contacto),
            ContactoModel("Cristopher Hernández", "5610307569", "cristopher.hernandez@gmail.com", R.drawable.contacto),
            ContactoModel("Lorena Carmona", "5532547620", "lorena.carmona@gmail.com", R.drawable.contacto),
            ContactoModel("Rodrigo Cruz", "5643572871", "rodrigo.cruz@gmail.com", R.drawable.contacto)
        )
        contactosFiltrados = contactos.toMutableList()

        contactAdapter = ContactAdapter(this, contactosFiltrados)
        listView.adapter = contactAdapter

        // Configurar el SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filtrarContactos(newText)
                return true
            }
        })
    }

    private fun filtrarContactos(query: String?) {
        contactosFiltrados.clear()
        if (query.isNullOrEmpty()) {
            contactosFiltrados.addAll(contactos)
        } else {
            val textoBusqueda = query.lowercase()
            for (contacto in contactos) {
                if (contacto.nombre.lowercase().contains(textoBusqueda) ||
                    contacto.telefono.contains(textoBusqueda)) {
                    contactosFiltrados.add(contacto)
                }
            }
        }
        contactAdapter.notifyDataSetChanged()
    }
}
