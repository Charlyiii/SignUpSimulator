package gz.ferreiro.barros.loginsimulator.controller

import android.view.View
import gz.ferreiro.barros.loginsimulator.model.UserManager

class FABclic(val userMan: UserManager) : View.OnClickListener {
    override fun onClick(boton: View) {
        userMan.printUsers()
    }
}
