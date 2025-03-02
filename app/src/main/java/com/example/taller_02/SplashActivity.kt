package com.example.taller_02

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d("CICLO_VIDA", "SplashActivity -> onCreate")

        // Retraso de 5 segundos antes de ir a HomeActivity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
            Log.d("CICLO_VIDA", "SplashActivity -> Navegando a HomeActivity y finalizando SplashActivity")
        }, 5000)
    }


    override fun onStart() {
        super.onStart()
        Log.d("CICLO_VIDA", "SplashActivity -> onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CICLO_VIDA", "SplashActivity -> onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CICLO_VIDA", "SplashActivity -> onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CICLO_VIDA", "SplashActivity -> onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("CICLO_VIDA", "SplashActivity -> onDestroy")
    }
}
