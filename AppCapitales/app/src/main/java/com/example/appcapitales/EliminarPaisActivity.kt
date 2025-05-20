package com.example.appcapitales

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appcapitales.databinding.ActivityEliminarPaisBinding

class EliminarPaisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEliminarPaisBinding
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarPaisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHelper(this)

        binding.btnBorrarPais.setOnClickListener {
            val pais = binding.editPais.text.toString()
            val cantidad = db.eliminarPorPais(pais)
            binding.editPais.setText("$cantidad ciudades eliminadas")
        }
    }
}