package gz.ferreiro.barros.loginsimulator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.text.util.Linkify
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import gz.ferreiro.barros.loginsimulator.controller.BotonRegistroClick
import gz.ferreiro.barros.loginsimulator.controller.FABclic
import gz.ferreiro.barros.loginsimulator.databinding.ActivityMainBinding
import gz.ferreiro.barros.loginsimulator.model.UserManager
import gz.ferreiro.barros.loginsimulator.model.Usuario

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //variables
        val bottomBar = binding.bottomAppBar
        val texto = binding.textView
        val inputUsuario = binding.textInputUsuario
        val layoutUsuario = binding.textLayoutUsuario
        val inputPass = binding.textInputPassword
        val layoutPass = binding.textLayoutPassword
        val botonReg = binding.botonRegistro
        val userMan = UserManager(texto)
        val fab = binding.fab

        //Damos funcionalidad al botón del menú de la barra inferior
        bottomBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.info -> {
                    clickInfo(texto)
                    true
                }
                R.id.clean ->{
                    texto.text = ""
                    true
                }
                else -> false
            }
        }

        //Damos funcionalidad al botón de registro
        val clicReg = BotonRegistroClick(
            this, inputUsuario,
            inputPass, userMan
        )
        botonReg.setOnClickListener(clicReg)

        //Damos funcionalidad al botón de mostrar usuarios
        val clicFab= FABclic(userMan)
        fab.setOnClickListener(clicFab)

        //Implementamos lo que queremos que pase al perder el foco, configuramos los errores
        //Usuario
        inputUsuario.setOnFocusChangeListener { editText, conFoco ->

            if (conFoco) {
                layoutUsuario.error = null
                return@setOnFocusChangeListener
            }

            when ((editText as TextInputEditText).length()) {
                in 1..2 -> layoutUsuario.error = getString(R.string.errorUsuario)
                else -> layoutUsuario.error = null
            }
        }
        //Contraseña: aquí avisa mientras escribes
        inputPass.addTextChangedListener {
            it.let {
                when (it?.length) {
                    in 1..6 -> layoutPass.error = getString(R.string.errorPass)
                    else -> layoutPass.error = null
                }
            }
        }
        //Quitamos el foco de cualquier fuera
        val constraint = binding.constraint
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        constraint.setOnClickListener {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            currentFocus?.clearFocus()
        }
    }

    //Métodos:
    //Para el clic en info
    private fun clickInfo(texto: TextView) {
        val linkedin = getString(R.string.linkedin)
        val github = getString(R.string.github)
        val telegram = getString(R.string.telegram)
        texto.text = "${getString(R.string.infoDesarrollador)}\n$linkedin\n$github\n$telegram\n\n\n\n"
        Linkify.addLinks(texto, Linkify.WEB_URLS)
    }
}