package gz.ferreiro.barros.loginsimulator.model

import android.util.Log
import android.widget.TextView

class UserManager(var texto: TextView) {
    private val mapa = mutableMapOf<String, Usuario>()

    fun getMapa(): MutableMap<String, Usuario> {
        return mapa
    }
    fun addUser(user: Usuario) {
        mapa[user.nombre] = user
    }

    fun printUsers() {
        if(mapa.isNotEmpty()){
        var count = 1
       texto.text = ""
       texto.text = "---------------LISTA DE USUARIOS REGISTRADOS----------------\n\n\n\n\n"
        mapa.forEach { (nombre, usuario) ->
        texto.append("$count. $nombre --- Contraseña: ${usuario.password}\n")
        count++
        }
        Log.d("mapaa", "$mapa")
        }
        else{
            texto.text = ""
            texto.text = "No hay usuarios registrados\n\n\n\n\n\n"
        }
    }
}
