# Bienvenidos a un nuevo minitutorial de Android Studio

Como ya vieron en el titulo vamos a crear una pequeña aplicación que nos permitirá enviar un correo electronico el cual incluirá el correo, asunto, mensaje.

## activity_main.xml

En nuestro activity_main.xml agregaremos un conjunto de linearLayout donde en cada uno le agregaremos un editText en total agregaremos 3 editText(correo, asunto, mensaje) y un botón quedando de la siguiente manera.

```

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:paddingLeft="16dp"
    android:paddingRight="16dp"
    tools:context=".MainActivity">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <EditText
            android:id="@+id/EtCorreo"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:layout_marginTop="20dp"
            android:ems="10"
            android:hint="@string/correo_ejemplo"
            android:inputType="textEmailAddress"
            android:minHeight="48dp" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <EditText
            android:id="@+id/EtAsunto"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:ems="10"
            android:hint="@string/Asunto"
            android:inputType="textPersonName"
            tools:ignore="TouchTargetSizeCheck,TouchTargetSizeCheck" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <EditText
            android:id="@+id/EtMensaje"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:ems="10"
            android:lines="10"
            android:hint="@string/Mensaje"
            android:gravity="start|top"
            android:inputType="textMultiLine" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btnEnviar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="@string/btnEnviar" />
    </LinearLayout>
</LinearLayout>

```

Como se podrán dar cuenta ocupamos hint en lugar de text para que puedan escribir sin borrar nada y que sepan que es lo que tienen que llenar en cada campo.

No todos los editText son iguales ya ocuparemos diferentes para cada campo como por ejemplo:

- textEmailAddress(Correo)
- textPersonName(asunto)
- textMultiLine(mensaje) 

Y agregaremos sus id's correspondientes (EtCorreo, EtAsunto, EtMensaje, btnEnviar).

## MainActivity

En el MainActivity crearemos 3 variables tipo EditText y 1 tipo Button.

```
  Button btnEnviar;
  EditText EtCorreo , EtMensaje, EtAsunto;
```

En el método onCreate() le asignaremos los ids a esas variables con el método findViewById(R.id.NOMBRE_DEL_ID) quedando de la siguiente manera.

```
   EtCorreo = findViewById(R.id.EtCorreo);
   EtAsunto = findViewById(R.id.EtAsunto);
   EtMensaje = findViewById(R.id.EtMensaje);
   btnEnviar = findViewById(R.id.btnEnviar);
```

Posteriormente agregaremos al botón el método setOnClickListener

```

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// AQUÍ ESCRIBIREMOS EL CÓDIGO PARA ENVIAR EL CORREO
            }
        });
```

Lo primero que haremos es crearemos una variable tipo String para correo, asunto y mensaje

```
      String correo = EtCorreo.getText().toString();
      String asunto = EtAsunto.getText().toString();
      String mensaje = EtMensaje.getText().toString();

```

Posteriormente revisaremos si el campo ha sido llenado y en caso contrario agregaremos un Toast indicando que falta de llenar ese campo quedando el código de la siguiente manera.
 
```
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
```

En caso de que todos los campos estén llenos lo que haremos es enviar el correo y para ello ocuparemos un intent tipo ACTION_SEND
al cual mendiante el metodo *putExtra* le pasaremos los parámetros mensaje, asunto y texto y el tipo *message/rfc822* el cual nos permitirá enviar el mensaje 


```
   Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL,
                            new String[]{correo});
                    intent.putExtra(Intent.EXTRA_SUBJECT,asunto);
                    intent.putExtra(Intent.EXTRA_TEXT, mensaje);
                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Elije un cliente de correo:"));

```

Con esto que hemos agregado el código final del MainActivity les tuvo que quedar de la siguiente manera.


```
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
```


Si desean ver todo el codigo [da click aquí](https://github.com/fynio/enviar_correo_android_studio.git)


Con esto hemos terminado de realizar todo el código espero que les sirva y esperen más mini tutoriales de android studio.

## ¡¡¡Hasta la proxima!!!



