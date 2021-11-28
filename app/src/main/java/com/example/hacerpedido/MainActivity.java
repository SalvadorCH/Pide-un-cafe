package com.example.hacerpedido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {private TextView indicadorCantidad;
    private TextView indicadorPrecio;
    private CheckBox toppingChocolate;
    private CheckBox toppingCanela;
    private int cantidadPedidos = 0;
    private double precioPedido = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicadorCantidad = findViewById(R.id.indicadorCantidad);
        indicadorPrecio = findViewById(R.id.indicadorPrecio);
        toppingChocolate = findViewById(R.id.checkboxChocolate);
        toppingCanela = findViewById(R.id.checkboxCanela);
    }

    public void sumaUnPedido(View v) {
        cantidadPedidos++;
        indicadorCantidad.setText(Integer.toString(cantidadPedidos));
        calculaPrecio();
    }

    public void restaUnPedido(View v) {
        if (cantidadPedidos > 0) {
            cantidadPedidos--;
            indicadorCantidad.setText(Integer.toString(cantidadPedidos));
            calculaPrecio();
        } else {
            Toast.makeText(this, "na", Toast.LENGTH_SHORT).show();
        }
    }

    public void calculaPrecioDos(View view){
        calculaPrecio();
    }

    private void calculaPrecio() {
        if (toppingChocolate.isChecked() && toppingCanela.isChecked()) {
            precioPedido = 4.5 * cantidadPedidos;
        } else if (toppingChocolate.isChecked() || toppingCanela.isChecked()) {
            precioPedido = 3.5 * cantidadPedidos;
        } else {
            precioPedido = 2.5 * cantidadPedidos;
        }
        indicadorPrecio.setText(Double.toString(precioPedido) + " €");
    }

    public void enviaMensajePedido(View v) {
        String mensaje = "Has pedido:\n";
        mensaje += cantidadPedidos + " cafes";
        if (cantidadPedidos > 0) {
            if (toppingChocolate.isChecked()) {
                mensaje += " con chocolate";
            }
            if (toppingChocolate.isChecked() && toppingCanela.isChecked()) {
                mensaje += " y";
            }
            if (toppingCanela.isChecked()) {
                mensaje += " con canela";
            }
            mensaje += "\nEl precio es de " + precioPedido + "€";

        }
        intentEmail("asunto", mensaje);
    }

    public void intentEmail(String subject, String cuerpoMensaje) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);
        startActivity(intent);
    }

}