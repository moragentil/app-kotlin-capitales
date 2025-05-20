package com.example.appcapitales

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityEliminarCiudadBinding

class EliminarCiudadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarCiudadBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnBorrarCiudad.setOnClickListener {
            val ciudad = binding.editCiudad.text.toString()
            val exito = db.eliminarPorCiudad(ciudad)
            binding.editCiudad.setText(if (exito) "Ciudad eliminada" else "No se encontró la ciudad")
        }
    }
}