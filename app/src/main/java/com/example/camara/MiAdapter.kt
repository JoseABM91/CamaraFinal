package com.example.camara

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MiAdapter(private val activity: Activity, private val listaElementos: ArrayList<DataItem>): BaseAdapter() {

    class ViewHolder{

        lateinit var nombre: TextView;
        lateinit var apellido: TextView;
        lateinit var profesion: TextView;

    }

    override fun getCount(): Int {
       return listaElementos.size;
    }
    override fun getItem(p0: Int): Any {
       return listaElementos[p0];
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong();
    }


    override fun getView(indice: Int, view: View?, p2: ViewGroup?): View {
    /////////////
        var  filaView= view;
        var viewHolder = ViewHolder();

        if(filaView == null){
            var inflater = activity.layoutInflater;
            filaView = inflater.inflate(R.layout.elementos, null, true);

            viewHolder.nombre = filaView.findViewById<TextView>(R.id.textViewNombre);
            viewHolder.apellido = filaView.findViewById<TextView>(R.id.textViewApellido);
            viewHolder.profesion = filaView.findViewById<TextView>(R.id.textViewProfesion);


            filaView.tag = viewHolder;
        }
        else{
            viewHolder = filaView.tag as MiAdapter.ViewHolder
        }
        viewHolder.nombre.text = listaElementos[indice].nombre;
        viewHolder.apellido.text = listaElementos[indice].apellido;
        viewHolder.profesion.text = listaElementos[indice].apellido;


        return filaView!!;
    }
}

