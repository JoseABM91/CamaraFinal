package com.example.camara

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    var listaElementos: ArrayList<DataItem> = ArrayList();
    lateinit var editTextNombre: EditText;
    lateinit var editTextApellido: EditText;
    lateinit var editTextProfesion: EditText;

    private val REQUEST_IMAGE_CAPTURE = 1
    private val PERMISSION_REQUEST_CODE = 101
    private var bitmapPhoto: Bitmap? = null
    lateinit var imageViewFoto: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageViewFoto = findViewById(R.id.imageViewFoto);

        // listaElementos = crearListaElementos();
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextProfesion = findViewById(R.id.editTextProfesion);
    }
    /* fun crearListaElementos(): ArrayList<DataItem> {
        var listaElementos = ArrayList<DataItem>();
        {
            listaElementos.add(DataItem("Nombre$i", "Apellido$i", "Profesion"));
        }
        return listaElementos;
    }*/

    fun guardar(view: View) {
        var builder: AlertDialog.Builder = AlertDialog.Builder(this);
        builder.setTitle("¿Deseas guardar tus datos?");
        var nombre = editTextNombre.text.toString()
        var apellido = editTextNombre.text.toString()
        var profesion = editTextNombre.text.toString()
        builder.setMessage("Nombre: $nombre Apellidos: $apellido Profesion: $profesion");

        builder.setPositiveButton("Si") { view, p1 ->
            var nombre = editTextNombre.text.toString()
            var mensaje = "Bienvenid@ $nombre"
            Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG).show();

            listaElementos.add(
                DataItem(
                    editTextNombre.text.toString(),
                    editTextApellido.text.toString(),
                    editTextProfesion.text.toString()
                )
            );
        }
        builder.setNegativeButton("No") { view, p1 ->
            var mensaje = "Eliminando datos"
            Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG).show();
            fun dismiss() {
                dismiss()
            }

        }
        builder.show();
    }


    fun VerListado(view: View) {
        var lista: Intent = Intent(this, MainActivity2::class.java)
        //GUARDAR LISTA listaElementos
        var gson = Gson();
        var data = gson.toJson(listaElementos);
        lista.putExtra("Lista", data);
        var a = 0;
        startActivity(lista)
    }


    fun salir(view: View) {
        finish()
    }
    fun botonCamara(view: View) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            takePicture();
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                PERMISSION_REQUEST_CODE
            )
        }
    }
    private fun takePicture() {
        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    takePicture()
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                bitmapPhoto = data!!.extras!!.get("data") as Bitmap
                imageViewFoto.setImageBitmap(bitmapPhoto)
            }
        }

    }
}


