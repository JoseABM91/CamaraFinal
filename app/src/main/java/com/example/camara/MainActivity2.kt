package com.example.camara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }
}

    /*
    var listaElementos:ArrayList<DataItem> = ArrayList();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listViewInicial: ListView = findViewById(R.id.listViewInicial);


        listaElementos = crearListaElementos();

        var miAdaptador: MiAdapter = MiAdapter(this, listaElementos);

        listViewInicial.adapter = miAdaptador;

        listViewInicial.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, posicion: Int, identificador: Long) {
                var nombre = listaElementos[posicion].nombre
                var apellido = listaElementos[posicion].apellido
                Toast.makeText(applicationContext, "Nombre $nombre Apellido: $apellido", Toast.LENGTH_LONG).show();
            }
        }
    }

    fun crearListaElementos(): ArrayList<DataItem>{
        var listaElementos = ArrayList<DataItem>();
        for (i in 0..25){
            listaElementos.add(DataItem("Nombre$i", "Apellido%¡$i", "es"));
        }
        listaElementos[0].bandera = "en";
        listaElementos[3].bandera = "en";
        listaElementos[5].bandera = "en";
        listaElementos[7].bandera = "en";
        listaElementos[14].bandera = "en";
        return listaElementos;
    }
}*/