package com.example.taller_02

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombres = findViewById<EditText>(R.id.etNombres)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val etRepetirContrasena = findViewById<EditText>(R.id.etRepetirContrasena)
        val checkTerminos = findViewById<CheckBox>(R.id.checkTerminos)
        val btnRegistro = findViewById<Button>(R.id.btnRegistro)
        val txtLogin = findViewById<TextView>(R.id.txtLogin)

        // Instancia de SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        btnRegistro.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val telefono = etTelefono.text.toString().trim()
            val contrasena = etContrasena.text.toString()
            val repetirContrasena = etRepetirContrasena.text.toString()

            if (validarCampos(nombres, apellidos, correo, telefono, contrasena, repetirContrasena, checkTerminos)) {
                // Verificar si el usuario ya está registrado
                val usuariosRegistrados = sharedPreferences.getStringSet("correosRegistrados", mutableSetOf()) ?: mutableSetOf()

                if (usuariosRegistrados.contains(correo)) {
                    Toast.makeText(this, "Este correo ya está registrado", Toast.LENGTH_SHORT).show()
                } else {
                    // Guardar datos del usuario en SharedPreferences
                    val editor = sharedPreferences.edit()

                    // Asegurarse de hacer una copia mutable del conjunto antes de modificarlo
                    val usuariosRegistrados = sharedPreferences.getStringSet("correosRegistrados", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

                    usuariosRegistrados.add(correo)

                    editor.putStringSet("correosRegistrados", usuariosRegistrados)
                    editor.putString("nombres_$correo", nombres)
                    editor.putString("apellidos_$correo", apellidos)
                    editor.putString("telefono_$correo", telefono)
                    editor.putString("contrasena_$correo", contrasena)
                    editor.putString("usuario_actual", correo)

                    editor.putString("usuario_actual", correo) // Guarda el correo del usuario como "usuario_actual"


                    editor.apply()

                    // Mostrar mensaje para verificar que se guardó el usuario actual
                    Toast.makeText(this, "Usuario registrado: $correo", Toast.LENGTH_SHORT).show()


                    // Redirigir a DashboardActivity
                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra("nombre_usuario", nombres) // Pasamos el nombre del usuario
                    startActivity(intent)
                    finish()
                }
            }
        }

        txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCampos(
        nombres: String,
        apellidos: String,
        correo: String,
        telefono: String,
        contrasena: String,
        repetirContrasena: String,
        checkTerminos: CheckBox
    ): Boolean {
        var esValido = true

        if (nombres.isEmpty()) {
            findViewById<EditText>(R.id.etNombres).error = "Este campo es obligatorio"
            esValido = false
        }

        if (apellidos.isEmpty()) {
            findViewById<EditText>(R.id.etApellidos).error = "Este campo es obligatorio"
            esValido = false
        }

        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            findViewById<EditText>(R.id.etCorreo).error = "Ingrese un correo válido"
            esValido = false
        }

        if (telefono.isEmpty() || telefono.length < 7) {
            findViewById<EditText>(R.id.etTelefono).error = "Ingrese un teléfono válido"
            esValido = false
        }

        if (contrasena.length < 6) {
            findViewById<EditText>(R.id.etContrasena).error = "La contraseña debe tener al menos 6 caracteres"
            esValido = false
        }

        if (contrasena != repetirContrasena) {
            findViewById<EditText>(R.id.etRepetirContrasena).error = "Las contraseñas no coinciden"
            esValido = false
        }

        if (!checkTerminos.isChecked) {
            checkTerminos.error = "Debes aceptar los términos y condiciones"
            esValido = false
        }

        return esValido
    }
}
