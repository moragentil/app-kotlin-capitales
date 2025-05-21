package com.example.appcapitales

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "ciudades.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE capitales (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                pais TEXT,
                ciudad TEXT UNIQUE,
                poblacion INTEGER
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS capitales")
        onCreate(db)
    }

    fun insertarCapital(pais: String, ciudad: String, poblacion: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("pais", pais)
            put("ciudad", ciudad)
            put("poblacion", poblacion)
        }

        val resultado = db.insert("capitales", null, values)

        Log.d("DEBUG_INSERTAR", "Intentando insertar: $values")
        if (resultado == -1L) {
            Log.e("DEBUG_INSERTAR", "Fallo el insert. Revisa si la ciudad ya existe o si la BD tiene error.")
        }

        return resultado != -1L
    }

    fun consultarCapital(nombreCiudad: String): String? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM capitales WHERE ciudad = ?", arrayOf(nombreCiudad))
        return if (cursor.moveToFirst()) {
            val pais = cursor.getString(cursor.getColumnIndexOrThrow("pais"))
            val poblacion = cursor.getInt(cursor.getColumnIndexOrThrow("poblacion"))
            cursor.close()
            "País: $pais\nPoblación: $poblacion"
        } else {
            cursor.close()
            null
        }
    }

    fun eliminarPorCiudad(ciudad: String): Boolean {
        val db = writableDatabase
        return db.delete("capitales", "ciudad = ?", arrayOf(ciudad)) > 0
    }

    fun eliminarPorPais(pais: String): Int {
        val db = writableDatabase
        return db.delete("capitales", "pais = ?", arrayOf(pais))
    }

    fun modificarPoblacion(ciudad: String, nuevaPoblacion: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("poblacion", nuevaPoblacion)
        }
        return db.update("capitales", values, "ciudad = ?", arrayOf(ciudad)) > 0
    }
    fun existeCiudad(ciudad: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT 1 FROM capitales WHERE LOWER(ciudad) = LOWER(?) LIMIT 1",
            arrayOf(ciudad)
        )
        val existe = cursor.moveToFirst()
        cursor.close()
        return existe
    }
    fun insertarCapitalPrueba(): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("pais", "Argentina")
            put("ciudad", "Buenos Aires")
            put("poblacion", 3000000)
        }
        val id = db.insert("capitales", null, values)
        Log.d("DBTest", "Insert ID: $id")
        return id != -1L
    }

}