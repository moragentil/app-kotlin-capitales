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

        binding.btnVolver.setOnClickListener {
            finish()  // Cierra esta actividad y vuelve a la anterior
        }

        binding.btnBorrarCiudad.setOnClickListener {
            val ciudad = binding.editCiudad.text.toString().trim()

            if (ciudad.isEmpty()) {
                binding.editCiudad.error = "Ingrese una ciudad"
                return@setOnClickListener
            }

            // Mostrar diálogo de confirmación
            val builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("Confirmar eliminación")
            builder.setMessage("¿Estás seguro de que querés eliminar la ciudad \"$ciudad\"?")

            builder.setPositiveButton("Sí") { _, _ ->
                val exito = db.eliminarPorCiudad(ciudad)
                if (exito) {
                    binding.editCiudad.setText("")
                    binding.editCiudad.hint = "Ciudad eliminada correctamente"
                } else {
                    binding.editCiudad.error = "No se encontró la ciudad"
                }
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()
        }

    }
}