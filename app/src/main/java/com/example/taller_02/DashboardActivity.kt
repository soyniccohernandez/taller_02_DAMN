package com.example.taller_02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val txtBienvenida = findViewById<TextView>(R.id.txtBienvenida)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)
        val correoUsuario = sharedPreferences.getString("usuario_actual", "")

        // Obtener nombre del usuario para la bienvenida
        val nombreUsuario = sharedPreferences.getString("nombres_$correoUsuario", "Usuario")
        txtBienvenida.text = "Bienvenido, $nombreUsuario"

        btnPerfil.setOnClickListener {
            val intent = Intent(this, PefilActivity::class.java)
            startActivity(intent)
        }

        btnCerrarSesion.setOnClickListener {
            // Limpiar el usuario actual de SharedPreferences
            val editor = sharedPreferences.edit()
            editor.remove("usuario_actual")
            editor.apply()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
