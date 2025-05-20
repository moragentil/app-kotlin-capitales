package com.example.appcapitales

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgregar.setOnClickListener {
            startActivity(Intent(this, AgregarCiudadActivity::class.java))
        }
        binding.btnBuscar.setOnClickListener {
            startActivity(Intent(this, BuscarCiudadActivity::class.java))
        }
        binding.btnBorrarCiudad.setOnClickListener {
            startActivity(Intent(this, EliminarCiudadActivity::class.java))
        }
        binding.btnBorrarPais.setOnClickListener {
            startActivity(Intent(this, EliminarPaisActivity::class.java))
        }
        binding.btnModificar.setOnClickListener {
            startActivity(Intent(this, ModificarCiudadActivity::class.java))
        }
    }
}
