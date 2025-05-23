package com.example.mobilbus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegistrarSupervisorFragment : Fragment() {

    private lateinit var inputNombres: EditText
    private lateinit var inputApellidos: EditText
    private lateinit var inputDNI: EditText
    private lateinit var inputUsuario: EditText
    private lateinit var inputContrasena: EditText
    private lateinit var btnGuardar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registrar_supervisor, container, false)

        inputNombres = view.findViewById(R.id.inputNombres)
        inputApellidos = view.findViewById(R.id.inputApellidos)
        inputDNI = view.findViewById(R.id.inputDNI)
        inputUsuario = view.findViewById(R.id.inputUsuario)
        inputContrasena = view.findViewById(R.id.inputContrasena)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            val nombres = inputNombres.text.toString()
            val apellidos = inputApellidos.text.toString()
            val dni = inputDNI.text.toString()
            val usuario = inputUsuario.text.toString()
            val contrasena = inputContrasena.text.toString()

            Toast.makeText(requireContext(), "Guardando supervisor: $nombres $apellidos $dni $usuario $contrasena", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}