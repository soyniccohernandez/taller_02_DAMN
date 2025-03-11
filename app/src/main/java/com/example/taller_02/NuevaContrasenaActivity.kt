package com.example.taller_02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NuevaContrasenaActivity : AppCompatActivity() {

    private lateinit var etNuevaContrasena: EditText
    private lateinit var btnActualizarContrasena: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_contrasena)  // Asegúrate de que el nombre del layout es correcto

        sharedPreferences = getSharedPreferences("Usuarios", MODE_PRIVATE)

        etNuevaContrasena = findViewById(R.id.etNuevaContrasena)
        btnActualizarContrasena = findViewById(R.id.btnActualizarContrasena)

        // Obtener el correo del intent
        val correo = intent.getStringExtra("correo") ?: ""

        btnActualizarContrasena.setOnClickListener {
            val nuevaContrasena = etNuevaContrasena.text.toString().trim()

            if (nuevaContrasena.isEmpty()) {
                Toast.makeText(this, "Ingrese una nueva contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nuevaContrasena.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (correo.isNotEmpty()) {
                val editor = sharedPreferences.edit()
                editor.putString("contrasena_$correo", nuevaContrasena) // Guardar la nueva contraseña
                editor.apply()

                Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_LONG).show()

                // Redirigir a la pantalla de inicio de sesión
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error: No se encontró el correo", Toast.LENGTH_LONG).show()
            }
        }
    }
}
