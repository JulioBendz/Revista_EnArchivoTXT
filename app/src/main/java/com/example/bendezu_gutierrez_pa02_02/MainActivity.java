package com.example.bendezu_gutierrez_pa02_02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText ContenidoR, TituloR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContenidoR = (EditText) findViewById(R.id.editTextContenido);
        TituloR = (EditText) findViewById(R.id.editTextTitulo);

        String[] archivos = fileList();
        if (validar(archivos, "Revista.txt")) {
            String texto = "";
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("Revista.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while (linea != null) {
                    texto = texto + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();

                ContenidoR.setText("\n \n Título: " + texto);


            } catch (IOException e) {
                Toast.makeText(this, "Error de lectura de revista", Toast.LENGTH_LONG).show();

            }
        }
    }
        private boolean validar(String[] archivos, String   buscarArchivo){
            for(int f=0;f<archivos.length;f++){
                if(buscarArchivo.equals(archivos[f])){
                    return true;
                }
            }
            return false;

        }

            public void grabar(View view){
            try{
                OutputStreamWriter archivo =new OutputStreamWriter(openFileOutput("Revista.txt", Activity.MODE_PRIVATE));

                archivo.write(TituloR.getText().toString()+"\n \n"+ ContenidoR.getText().toString());

                archivo.flush();
                archivo.close();
                Toast.makeText(this,"Se guardo revista exitosamente", Toast.LENGTH_LONG).show();

            }catch (IOException e){
                Toast.makeText(this, "No se puedo guardar datos de la revista", Toast.LENGTH_LONG).show();
            }
            finish();


    }
}