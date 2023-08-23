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
        builder.setMessage("Nombre: $editTextNombre Apellidos: $editTextApellido Profesion: $editTextProfesion");

        builder.setPositiveButton("Si") { view, p1 ->
            var mensaje = "Bienvenid@ $editTextNombre"
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
        }
        builder.show();
    }

    fun VerListado(view: View) {


        var lista: Intent = Intent(this, MainActivity2::class.java)
        //GUARDAR LISTA listaElementos
        var gson = Gson();
        var data = gson.toJson(lista);
        var lista2 = gson.fromJson(data, ArrayList<DataItem>);
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
            botonCamara();
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


