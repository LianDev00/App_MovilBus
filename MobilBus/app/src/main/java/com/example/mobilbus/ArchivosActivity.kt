package com.example.mobilbus

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArchivosActivity : AppCompatActivity(), ArchivosAdapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archivos)

        // Lista de ejemplo de archivos
        val archivos = listOf("Archivo 1", "Archivo 2", "Archivo 3", "Archivo 4")

        // Configurar RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewArchivos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Configurar Adapter
        val adapter = ArchivosAdapter(archivos, this)
        recyclerView.adapter = adapter
    }

    // Acción cuando se marca o desmarca un archivo
    override fun onItemChecked(archivo: String, isChecked: Boolean) {
        Toast.makeText(this, "Archivo: $archivo, Seleccionado: $isChecked", Toast.LENGTH_SHORT).show()
    }
}
