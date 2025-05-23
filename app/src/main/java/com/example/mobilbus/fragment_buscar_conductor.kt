package com.example.mobilbus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class BuscarConductorFragment : Fragment() {

    private lateinit var inputNombresBuscar: EditText
    private lateinit var inputApellidosBuscar: EditText
    private lateinit var inputDNIBuscar: EditText
    private lateinit var inputUsuarioBuscar: EditText
    private lateinit var inputContrasenaBuscar: EditText
    private lateinit var btnGuardarBuscar: Button
    private lateinit var btnEliminar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buscar_conductor, container, false)

        inputNombresBuscar = view.findViewById(R.id.inputNombresBuscar)
        inputApellidosBuscar = view.findViewById(R.id.inputApellidosBuscar)
        inputDNIBuscar = view.findViewById(R.id.inputDNIBuscar)
        inputUsuarioBuscar = view.findViewById(R.id.inputUsuarioBuscar)
        inputContrasenaBuscar = view.findViewById(R.id.inputContrasenaBuscar)
        btnGuardarBuscar = view.findViewById(R.id.btnGuardarBuscar)
        btnEliminar = view.findViewById(R.id.btnEliminar)

        btnGuardarBuscar.setOnClickListener {
            val dni = inputDNIBuscar.text.toString()
            Toast.makeText(requireContext(), "Guardando cambios para conductor con DNI: $dni", Toast.LENGTH_SHORT).show()
        }

        btnEliminar.setOnClickListener {
            val dni = inputDNIBuscar.text.toString()
            Toast.makeText(requireContext(), "Eliminando conductor con DNI: $dni", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}