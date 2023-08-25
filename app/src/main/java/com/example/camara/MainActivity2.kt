package com.example.camara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var gson = Gson();
        val itemType = object : TypeToken<ArrayList<DataItem>>() {}.type
        var data = intent.getStringExtra("Lista");
        var lista2:ArrayList<DataItem> = gson.fromJson(data, itemType);

        var listViewInicial: ListView = findViewById(R.id.ListaUsuarios);
        var miAdaptador: MiAdapter = MiAdapter(this, lista2);
        listViewInicial.adapter = miAdaptador;
    }


}

// fun botonCustomPulsado(view: View){
//        var miCustomDialog = CustomDialog();
//        miCustomDialog.mainActivity = this;
//        miCustomDialog.show(supportFragmentManager, "CustomEtiqueta");
//    }
//
//    fun actualizarUsuarioPassword(usuario: String, password: String){
//        textViewUsuario.text = usuario;
//        textViewPassword.text = password;
//    }