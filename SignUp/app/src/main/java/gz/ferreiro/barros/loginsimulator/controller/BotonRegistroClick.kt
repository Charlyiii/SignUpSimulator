package gz.ferreiro.barros.loginsimulator.controller

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import gz.ferreiro.barros.loginsimulator.MainActivity
import gz.ferreiro.barros.loginsimulator.model.UserManager
import gz.ferreiro.barros.loginsimulator.model.Usuario

class BotonRegistroClick(
    val contexto: Context,
    val inputUser: TextInputEditText,
    val inputPass: TextInputEditText,
    val userMan: UserManager
) : View.OnClickListener {
    override fun onClick(boton: View) {


        if (inputUser.length() > 2 && inputPass.length() > 6) {

            var user = Usuario(inputUser.text.toString(), inputPass.text.toString())

            if (userMan.getMapa().containsKey(user.nombre)) {
                Toast.makeText(contexto, "Ese usuario ya existe", Toast.LENGTH_LONG).show()
            }
            else if (inputUser.text.toString().contains(" ") ||
                inputPass.text.toString().contains(" ")
            ) {
                Toast.makeText(contexto, "El usuario y la contraseña no pueden tener espacios en blanco", Toast.LENGTH_LONG).show()
            }
            else {
                userMan.addUser(user)
                Toast.makeText(contexto, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()
                inputUser.text?.clear()
                inputPass.text?.clear()
            }
        } else Toast.makeText(contexto, "Error en el formulario", Toast.LENGTH_SHORT).show()
    }
}