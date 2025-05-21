package com.example.appcapitales

import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.btnVolver.setOnClickListener {
            finish()
        }

        binding.btnBuscar.setOnClickListener {
            val ciudad = binding.editCiudad.text.toString().trim()

            if (ciudad.isEmpty()) {
                Toast.makeText(this, "Ingrese una ciudad", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultado = db.consultarCapital(ciudad)

            if (resultado != null) {
                // Mostrar la tarjeta
                binding.layoutResultado.visibility = View.VISIBLE

                // Parsear el resultado
                val lineas = resultado.split("\n")
                val pais = lineas.getOrNull(0) ?: ""
                val poblacion = lineas.getOrNull(1) ?: ""

                binding.tvCiudadResultado.text = "Ciudad: $ciudad"
                binding.tvPaisResultado.text = pais
                binding.tvPoblacionResultado.text = poblacion
            } else {
                binding.layoutResultado.visibility = View.GONE
                Toast.makeText(this, "Ciudad no encontrada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
