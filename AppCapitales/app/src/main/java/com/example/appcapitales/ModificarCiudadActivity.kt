package com.example.appcapitales

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityModificarCiudadBinding

class ModificarCiudadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarCiudadBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnModificar.setOnClickListener {
            val ciudad = binding.editCiudad.text.toString()
            val nuevaPoblacion = binding.editPoblacion.text.toString().toIntOrNull()

            if (ciudad.isNotEmpty() && nuevaPoblacion != null) {
                val exito = db.modificarPoblacion(ciudad, nuevaPoblacion)
                binding.editPoblacion.setText(if (exito) "Población actualizada" else "Ciudad no encontrada")
            } else {
                binding.editPoblacion.setText("Campos incompletos")
            }
        }
    }
}