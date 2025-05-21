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

        binding.btnVolver.setOnClickListener {
            finish()  // Cierra esta actividad y vuelve a la anterior
        }

        binding.btnGuardar.setOnClickListener {
            val pais = binding.editPais.text.toString().trim()
            val ciudad = binding.editCiudad.text.toString().trim()
            val poblacion = binding.editPoblacion.text.toString().toIntOrNull()

            if (pais.isEmpty() || ciudad.isEmpty() || poblacion == null) {
                Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar que la ciudad no exista (supongo que tu DatabaseHelper tiene un método para esto)
            val existeCiudad = db.existeCiudad(ciudad)
            if (existeCiudad) {
                Toast.makeText(this, "La ciudad ya existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val exito = db.insertarCapital(pais, ciudad, poblacion)
            Toast.makeText(this, if (exito) "Ciudad agregada" else "Error al guardar", Toast.LENGTH_SHORT).show()
        }
    }
}