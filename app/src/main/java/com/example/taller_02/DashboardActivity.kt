package com.example.taller_02

import android.content.Intent
import android.os.Bundle
import android.content.SharedPreferences
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Botón para ir a la Calculadora de Salud
        val btnCalculadoraSalud = findViewById<Button>(R.id.btnCalculadoraSalud)
        btnCalculadoraSalud.setOnClickListener {
            val intent = Intent(this, CalculadoraSaludActivity::class.java)
            startActivity(intent)
        }

        // Botón para ir al Perfil
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            val intent = Intent(this, PefilActivity::class.java) // Verifica si es PerfilActivity o PefilActivity
            startActivity(intent)
        }

        // Botón para cerrar sesión
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }
    }

    private fun cerrarSesion() {
        // Eliminar los datos del usuario en SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("usuario_actual") // Elimina el usuario actual
        editor.apply()

        // Redirigir al Login y cerrar esta actividad
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Evita volver atrás
        startActivity(intent)
        finish()
    }
}
