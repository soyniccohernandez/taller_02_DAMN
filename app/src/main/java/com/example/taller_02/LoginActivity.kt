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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etCorreo = findViewById<EditText>(R.id.input_usuario)
        val etContrasena = findViewById<EditText>(R.id.input_pass)
        val btnIngresar = findViewById<Button>(R.id.btn_ingresar)
        val txtRegistrate = findViewById<TextView>(R.id.lnk_registrar)
        val lnkRecuperar = findViewById<TextView>(R.id.lnk_recuperar)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        btnIngresar.setOnClickListener {
            val correoIngresado = etCorreo.text.toString().trim()
            val contrasenaIngresada = etContrasena.text.toString()

            val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

            // Usar la clave dinámica basada en el correo ingresado para obtener los datos
            val contrasenaGuardada = sharedPreferences.getString("contrasena_$correoIngresado", null)

            if (contrasenaIngresada == contrasenaGuardada) {
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
