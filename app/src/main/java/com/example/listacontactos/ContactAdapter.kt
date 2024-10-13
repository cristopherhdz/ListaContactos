package com.example.listacontactos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Color

class ContactAdapter(private val context: Context, private val contactos: List<ContactoModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return contactos.size
    }

    override fun getItem(position: Int): Any {
        return contactos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false)

        val contact = contactos[position]

        // Referencias a los elementos del layout
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val nombreTextView: TextView = view.findViewById(R.id.textViewNombre)
        val telefonoTextView: TextView = view.findViewById(R.id.textViewTelefono)
        val emailTextView: TextView = view.findViewById(R.id.textViewEmail)
        val buttonEnviarCorreo: Button = view.findViewById(R.id.buttonEnviarCorreo)

        // Asignar datos a los elementos
        imageView.setImageResource(contact.imagenId)
        nombreTextView.text = contact.nombre
        telefonoTextView.text = contact.telefono
        emailTextView.text = contact.email

        // Cambiar color de fondo si hay duplicados
        if (contactos.count { it.telefono == contact.telefono } > 1) {
            view.setBackgroundColor(Color.YELLOW) // Cambia el color a amarillo para los duplicados
        } else {
            view.setBackgroundColor(Color.WHITE) // Color por defecto
        }

        // Configurar el botón para enviar correo
        buttonEnviarCorreo.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${contact.email}") // Solo para mostrar aplicaciones que puedan manejar esto
                putExtra(Intent.EXTRA_SUBJECT, "Hola desde la app")
                putExtra(Intent.EXTRA_TEXT, "Hola ${contact.nombre},")
            }
            context.startActivity(Intent.createChooser(emailIntent, "Enviar correo..."))
        }

        return view
    }
}

