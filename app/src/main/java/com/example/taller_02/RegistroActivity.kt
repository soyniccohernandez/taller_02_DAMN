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

        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioData", MODE_PRIVATE)

        btnRegistro.setOnClickListener {
            val nombres = etNombres.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim().lowercase()
            val telefono = etTelefono.text.toString().trim()
            val contrasena = etContrasena.text.toString()
            val repetirContrasena = etRepetirContrasena.text.toString()

            if (validarCampos(etNombres, etApellidos, etCorreo, etTelefono, etContrasena, etRepetirContrasena, checkTerminos)) {
                val usuariosRegistrados = sharedPreferences.getStringSet("correosRegistrados", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

                if (usuariosRegistrados.contains(correo)) {
                    Toast.makeText(this, "Este correo ya está registrado", Toast.LENGTH_SHORT).show()
                } else {
                    // Guardar datos
                    val editor = sharedPreferences.edit()
                    usuariosRegistrados.add(correo)

                    editor.putStringSet("correosRegistrados", usuariosRegistrados)
                    editor.putString("nombres_$correo", nombres)
                    editor.putString("apellidos_$correo", apellidos)
                    editor.putString("telefono_$correo", telefono)
                    editor.putString("contrasena_$correo", contrasena)

                    editor.commit() // Guardado seguro

                    Toast.makeText(this, "Usuario registrado: $correo", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, DashboardActivity::class.java)
                    intent.putExtra("nombre_usuario", nombres)
                    startActivity(intent)
                    finish()
                }
            }
        }

        txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun validarCampos(
        etNombres: EditText,
        etApellidos: EditText,
        etCorreo: EditText,
        etTelefono: EditText,
        etContrasena: EditText,
        etRepetirContrasena: EditText,
        checkTerminos: CheckBox
    ): Boolean {
        var esValido = true

        if (etNombres.text.isEmpty()) {
            etNombres.error = "Este campo es obligatorio"
            etNombres.requestFocus()
            esValido = false
        }

        if (etApellidos.text.isEmpty()) {
            etApellidos.error = "Este campo es obligatorio"
            etApellidos.requestFocus()
            esValido = false
        }

        if (etCorreo.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(etCorreo.text).matches()) {
            etCorreo.error = "Ingrese un correo válido"
            etCorreo.requestFocus()
            esValido = false
        }

        if (etTelefono.text.isEmpty() || etTelefono.text.length < 7) {
            etTelefono.error = "Ingrese un teléfono válido"
            etTelefono.requestFocus()
            esValido = false
        }

        if (etContrasena.text.length < 6) {
            etContrasena.error = "La contraseña debe tener al menos 6 caracteres"
            etContrasena.requestFocus()
            esValido = false
        }

        if (etContrasena.text.toString() != etRepetirContrasena.text.toString()) {
            etRepetirContrasena.error = "Las contraseñas no coinciden"
            etRepetirContrasena.requestFocus()
            esValido = false
        }

        if (!checkTerminos.isChecked) {
            Toast.makeText(checkTerminos.context, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
            esValido = false
        }

        return esValido
    }
}
