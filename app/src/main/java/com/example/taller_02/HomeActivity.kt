package com.example.taller_02

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("CICLO_VIDA", "HomeActivity -> onCreate")

        // Buscar el botón "Comienza" en el layout
        val btnComienza = findViewById<Button>(R.id.btnComienza)

        // Buscar el TextView "Regístrate"
        val txtRegistrate = findViewById<TextView>(R.id.txtRegistrate)

        // Configurar el click en "Comienza" para ir a LoginActivity
        btnComienza.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Log.d("CICLO_VIDA", "HomeActivity -> Navegando a LoginActivity")
        }

        // Configurar el click en "Regístrate" para ir a RegistroActivity
        txtRegistrate.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
            Log.d("CICLO_VIDA", "HomeActivity -> Navegando a RegistroActivity")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("CICLO_VIDA", "HomeActivity -> onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO_VIDA", "HomeActivity -> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLO_VIDA", "HomeActivity -> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLO_VIDA", "HomeActivity -> onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLO_VIDA", "HomeActivity -> onDestroy")
    }
}
