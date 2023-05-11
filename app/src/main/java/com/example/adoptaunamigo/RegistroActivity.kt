package com.example.adoptaunamigo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.registroButton
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.emailEditText
import kotlinx.android.synthetic.main.activity_registro.passwordEditText


class RegistroActivity : AppCompatActivity() {
    val Rol = arrayOf("Donante","Adoptante")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        setup()
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,Rol)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRol.adapter = adaptador
    }
    private fun setup(){
        title = "Registro de usuario"
        registroButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful){
                        showAuth()
                    }else{
                        showAlert()
                    }
                }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Los datos ingresados son incorrectos")
        builder.setPositiveButton("Aceptar",null)
        val dialog : AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAuth(){
        val muestraAuthIntent = Intent(this,AuthActivity::class.java)
        startActivity(muestraAuthIntent)
    }
}

