package com.example.appcapitales

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityBuscarCiudadBinding

class BuscarCiudadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuscarCiudadBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarCiudadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnBuscar.setOnClickListener {
            val ciudad = binding.editCiudad.text.toString()
            val resultado = db.consultarCapital(ciudad)
            binding.textResultado.text = resultado ?: "Ciudad no encontrada"
        }
    }
}