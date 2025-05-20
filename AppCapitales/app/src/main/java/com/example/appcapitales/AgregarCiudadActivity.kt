package com.example.appcapitales

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityAgregarCiudadBinding

class AgregarCiudadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarCiudadBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnGuardar.setOnClickListener {
            val pais = binding.editPais.text.toString()
            val ciudad = binding.editCiudad.text.toString()
            val poblacion = binding.editPoblacion.text.toString().toIntOrNull()

            if (pais.isNotEmpty() && ciudad.isNotEmpty() && poblacion != null) {
                val exito = db.insertarCapital(pais, ciudad, poblacion)
                Toast.makeText(this, if (exito) "Ciudad agregada" else "Error al guardar", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}