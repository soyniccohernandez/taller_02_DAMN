package com.example.taller_02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecuperacionActivity : AppCompatActivity() {

    private lateinit var etCorreo: EditText
    private lateinit var btnEnviar: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)

        sharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        etCorreo = findViewById(R.id.etCorreoRecuperacion)
        btnEnviar = findViewById(R.id.btnEnviarSolicitud)

        btnEnviar.setOnClickListener {
            val correo = etCorreo.text.toString().trim().lowercase() // Convertir a minúsculas

            if (correo.isEmpty()) {
                Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Recuperar la lista de correos registrados
            val correosRegistrados = sharedPreferences.getStringSet("correosRegistrados", mutableSetOf()) ?: mutableSetOf()

            if (correo in correosRegistrados) {
                Toast.makeText(this, "Correo enviado (simulado).", Toast.LENGTH_LONG).show()

                val intent = Intent(this, NuevaContrasenaActivity::class.java)
                intent.putExtra("correo", correo) // Pasar el correo al siguiente Activity
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo no registrado.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
