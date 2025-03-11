package com.example.taller_02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<EditText>(R.id.input_usuario)
        val etContrasena = findViewById<EditText>(R.id.input_pass)
        val btnIngresar = findViewById<Button>(R.id.btn_ingresar)
        val txtRegistrate = findViewById<TextView>(R.id.lnk_registrar)
        val lnkRecuperar = findViewById<TextView>(R.id.lnk_recuperar)

        sharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        btnIngresar.setOnClickListener {
            val correoIngresado = etCorreo.text.toString().trim().lowercase() // Estandarizar en minúsculas
            val contrasenaIngresada = etContrasena.text.toString().trim()

            if (correoIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese su correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Recuperar la contraseña guardada
            val contrasenaGuardada = sharedPreferences.getString("contrasena_$correoIngresado", null)

            if (contrasenaGuardada == null) {
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (contrasenaGuardada == contrasenaIngresada) {
                // Inicio de sesión exitoso
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                val editor = sharedPreferences.edit()
                editor.putString("usuario_actual", correoIngresado)
                editor.apply()

                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("nombre_usuario", sharedPreferences.getString("nombres_$correoIngresado", "Usuario"))
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        txtRegistrate.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        lnkRecuperar.setOnClickListener {
            val intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }
    }
}
