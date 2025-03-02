package com.example.taller_02

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PefilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Recuperar SharedPreferences
        val sharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        // Obtener el correo del usuario actual
        val correo = sharedPreferences.getString("usuario_actual", null)

        if (correo != null) {
            // Recuperar los datos usando el correo
            val nombre = sharedPreferences.getString("nombres_$correo", "Nombre no disponible")
            val apellido = sharedPreferences.getString("apellidos_$correo", "Apellido no disponible")
            val telefono = sharedPreferences.getString("telefono_$correo", "Teléfono no disponible")

            // Asignar los datos a los TextView
            findViewById<TextView>(R.id.txtNombre).text = nombre
            findViewById<TextView>(R.id.txtApellido).text = apellido
            findViewById<TextView>(R.id.txtCorreo).text = correo
            findViewById<TextView>(R.id.txtTelefono).text = telefono
        } else {
            // Si no hay usuario registrado, mostrar un mensaje de error
            findViewById<TextView>(R.id.txtNombre).text = "No hay usuario registrado"
            findViewById<TextView>(R.id.txtApellido).text = ""
            findViewById<TextView>(R.id.txtCorreo).text = ""
            findViewById<TextView>(R.id.txtTelefono).text = ""

            // Opcionalmente, redirigir a la actividad de inicio de sesión si no hay usuario registrado
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no pueda volver
        }
    }
}

