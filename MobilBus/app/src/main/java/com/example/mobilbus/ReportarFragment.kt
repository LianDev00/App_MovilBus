package com.example.mobilbus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReportarFragment : Fragment(), TareasAdapter.OnItemClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_reportar, container, false)

        // Obtener el RecyclerView
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerViewTareas)

        // Lista de ejemplo de tareas
        val tareas = listOf("Tarea 1", "Tarea 2", "Tarea 3", "Tarea 4")

        // Configurar el LayoutManager y Adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = TareasAdapter(tareas, this)
        recyclerView.adapter = adapter

        return rootView
    }

    // Acción cuando se selecciona una tarea
    override fun onItemClick(tarea: String) {
        Toast.makeText(context, "Seleccionaste la tarea: $tarea", Toast.LENGTH_SHORT).show()
        // Aquí puedes realizar cualquier acción adicional, como abrir una nueva pantalla
        // o mostrar detalles de la tarea seleccionada.
    }
}
