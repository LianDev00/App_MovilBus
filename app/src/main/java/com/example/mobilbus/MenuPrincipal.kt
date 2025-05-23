package com.example.mobilbus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilbus.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageBtnMarcaruta: ImageView = findViewById(R.id.imageBtnMarcaruta)
        val imageBtnTareas: ImageView = findViewById(R.id.imageBtnTareas)
        val imageBtnArchivos: ImageView = findViewById(R.id.imageBtnArchivos)
        val imageBtnMantenimiento: ImageView = findViewById(R.id.imageBtnMantenimiento)

        // Ejemplo: actualizar nombre del usuario
        val userName = "usernnn"
        binding.welcomeText.text = "Bienvenido $userName"

        // Acción para cerrar sesión
        binding.logoutText.setOnClickListener {
            // TODO: manejar cierre de sesión
            finish()
        }
        // Configura el listener de clic
        imageBtnMarcaruta.setOnClickListener {
            val intent = Intent(this, MarcarRutaActivity::class.java)
            startActivity(intent)
        }

        imageBtnTareas.setOnClickListener {
            val intent = Intent(this, TareasActivity::class.java)
            startActivity(intent)
        }

        imageBtnArchivos.setOnClickListener {
            val intent = Intent(this, ArchivosActivity::class.java)
            startActivity(intent)
        }

        imageBtnMantenimiento.setOnClickListener {
            val intent = Intent(this, MantenimientoActivity::class.java)
            startActivity(intent)
        }


    }
}
