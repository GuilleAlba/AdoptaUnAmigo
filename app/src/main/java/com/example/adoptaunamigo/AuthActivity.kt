package com.example.adoptaunamigo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setup()
    }
    private fun setup(){
        title = "Incio de sesion"
        ingresarButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString()).
                addOnCompleteListener {
                    if (it.isSuccessful){
                        showHomeDonante(it.result?.user?.email?:"",ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        }
        registroButton.setOnClickListener {
            showRegistro()
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

    private fun showHomeDonante(email:String,provider:ProviderType){
        val homeDonanteIntent = Intent(this,HomeDonanteActivity::class.java).apply {
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(homeDonanteIntent)
    }

    private fun showRegistro(){
        val muestraRegistroIntent = Intent(this,RegistroActivity::class.java)
        startActivity(muestraRegistroIntent)
    }

}