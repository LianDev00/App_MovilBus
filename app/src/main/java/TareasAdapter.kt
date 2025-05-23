package com.example.mobilbus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareasAdapter(
    private val tareasList: List<String>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareasList[position]
        holder.textViewTarea.text = tarea
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(tarea)
        }
    }

    override fun getItemCount(): Int {
        return tareasList.size
    }

    class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTarea: TextView = view.findViewById(R.id.textViewTarea)
    }

    interface OnItemClickListener {
        fun onItemClick(tarea: String)
    }
}

class ArchivosAdapter(
    private val archivosList: List<String>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ArchivosAdapter.ArchivoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchivoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_archivo, parent, false)
        return ArchivoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArchivoViewHolder, position: Int) {
        val archivo = archivosList[position]
        holder.tvArchivo.text = archivo
        holder.cbSelect.setOnCheckedChangeListener { _, isChecked ->
            itemClickListener.onItemChecked(archivo, isChecked)
        }
    }

    override fun getItemCount(): Int {
        return archivosList.size
    }

    class ArchivoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArchivo: TextView = view.findViewById(R.id.tvArchivo)
        val cbSelect: CheckBox = view.findViewById(R.id.cbSelect)
    }

    interface OnItemClickListener {
        fun onItemChecked(archivo: String, isChecked: Boolean)
    }
}
