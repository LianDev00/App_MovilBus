package com.example.mobilbus

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilbus.com.example.mobilbus.Tarea


class TareasAdapter(
    private var tareas: MutableList<Tarea>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

    inner class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvDetalle: TextView = itemView.findViewById(R.id.tvDetalle)
        val tvEstado: TextView = itemView.findViewById(R.id.tvEstado)
        val tvFechaLimite: TextView = itemView.findViewById(R.id.tvFechaLimite)
        val tvDniconductor: TextView = itemView.findViewById(R.id.tvDniconductor)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(tareas[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = tareas[position]
        holder.tvId.text = tarea.id
        holder.tvDetalle.text = tarea.detalle
        holder.tvEstado.text = tarea.estado
        holder.tvFechaLimite.text = tarea.fechaLimite
        holder.tvDniconductor.text = tarea.dniconductor
    }

    override fun getItemCount() = tareas.size

    fun actualizarTareas(nuevasTareas: List<Tarea>) {
        Log.i("Adapter", "Limpieza lista actual: ${tareas.size}")
        tareas.clear()
        Log.i("Adapter", "Lista limpiada, tamaño ahora: ${tareas.size}")
        tareas.addAll(nuevasTareas)
        Log.i("Adapter", "Agregados nuevos: ${nuevasTareas.size}, tamaño final: ${tareas.size}")
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(tarea: Tarea)
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
