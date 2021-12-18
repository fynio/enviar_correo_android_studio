package com.example.mandarcorreo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText EtCorreo , EtMensaje, EtAsunto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtCorreo = findViewById(R.id.EtCorreo);
        EtAsunto = findViewById(R.id.EtAsunto);
        EtMensaje = findViewById(R.id.EtMensaje);
        btnEnviar = findViewById(R.id.btnEnviar);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo = EtCorreo.getText().toString();
                String asunto = EtAsunto.getText().toString();
                String mensaje = EtMensaje.getText().toString();

                if(correo.equals(""))
                {
                    Toast.makeText(MainActivity.this,"ingresa un correo", Toast.LENGTH_LONG).show();
                }


                else if(asunto.equals(""))
                {
                    Toast.makeText(MainActivity.this,"ingresa el asunto", Toast.LENGTH_LONG).show();
                }


                else if(mensaje.equals(""))
                {
                    Toast.makeText(MainActivity.this, "ingresa un mensaje", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // Defino mi intent y hago uso del objeto ACTION_SEND
                 //   Intent intent = new Intent(Intent.ACTION_SENDTO);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{correo});
                    intent.putExtra(Intent.EXTRA_SUBJECT,asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Elije un cliente de correo:"));
                }
            }
        });
    }
}