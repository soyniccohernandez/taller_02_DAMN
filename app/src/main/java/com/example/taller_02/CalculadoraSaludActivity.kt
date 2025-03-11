package com.example.taller_02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculadoraSaludActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_salud)

        val etHorasTrabajo = findViewById<EditText>(R.id.etHorasTrabajo)
        val etTareasPendientes = findViewById<EditText>(R.id.etTareasPendientes)
        val etDificultadTareas = findViewById<EditText>(R.id.etDificultadTareas)
        val etTiempoDescanso = findViewById<EditText>(R.id.etTiempoDescanso)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)

        btnCalcular.setOnClickListener {
            val horasTrabajo = etHorasTrabajo.text.toString().toIntOrNull()
            val tareasPendientes = etTareasPendientes.text.toString().toIntOrNull()
            val dificultadTareas = etDificultadTareas.text.toString().toIntOrNull()
            val tiempoDescanso = etTiempoDescanso.text.toString().toIntOrNull()

            if (horasTrabajo == null || tareasPendientes == null || dificultadTareas == null || tiempoDescanso == null) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dificultadTareas !in 1..5) {
                Toast.makeText(this, "El nivel de dificultad debe estar entre 1 y 5.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // **Fórmulas para los cálculos**
            val porcentajeDificultad = when (dificultadTareas) {
                1 -> 0.8
                2 -> 0.9
                3 -> 1.0
                4 -> 1.2
                5 -> 1.5
                else -> 1.0
            }
            val distribucionTiempo = horasTrabajo * porcentajeDificultad
            val productividadEstimada = (100 - (dificultadTareas * 10) - (tiempoDescanso * 2)).coerceAtLeast(0)
            val tiempoTotalRequerido = (tareasPendientes * dificultadTareas * 1.5) + (tiempoDescanso * tareasPendientes)

            // **Mostrar resultados en el TextView**
            val resultadoTexto = """
                - Distribución óptima de tiempo: ${"%.2f".format(distribucionTiempo)} horas
                - Productividad estimada: $productividadEstimada%
                - Tiempo total requerido: ${"%.2f".format(tiempoTotalRequerido)} min
            """.trimIndent()

            txtResultado.text = resultadoTexto
            txtResultado.visibility = TextView.VISIBLE // Mostrar el resultado en pantalla
        }
    }
}
