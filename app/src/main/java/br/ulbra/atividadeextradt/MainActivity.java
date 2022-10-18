package br.ulbra.atividadeextradt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView txtNome, txtINSS, txtIR, txtSalLiq;
    EditText edNome, edSalBruto, edNFilhos;
    Button btCalcular;
    RadioGroup rgSexo;
    RadioButton rbMasc, rbFem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNome = (TextView) findViewById(R.id.txtNome);
        txtINSS = (TextView) findViewById(R.id.txtINSS);
        txtIR = (TextView) findViewById(R.id.txtIR);
        txtSalLiq = (TextView) findViewById(R.id.txtSalLiq);
        edNome = (EditText) findViewById(R.id.edNome);
        edSalBruto = (EditText) findViewById(R.id.edSalBruto);
        edNFilhos = (EditText) findViewById(R.id.edNFilhos);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        rbFem = (RadioButton) findViewById(R.id.rbFem);
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double nmrFilhos = Double.parseDouble(edNFilhos.getText().toString());
                double salBrt = Double.parseDouble(edSalBruto.getText().toString());
                String sexo = "";
                String nome = edNome.getText().toString();
                double descInss = 0;
                double descIr = 0;
                double acrescFil = 56.47 * nmrFilhos;
                DecimalFormat f = new DecimalFormat("#.##");
                if (rbFem.isChecked()) {
                    sexo = "Sra.";
                }
                else {
                    sexo = "Sr";
                }
                if (salBrt <= 1212.0) {
                    descInss = salBrt * 0.075;
                } else if (salBrt > 1212.0 && salBrt <= 2427.35) {
                    descInss = salBrt * 0.09;
                } else if (salBrt > 2427.35 && salBrt <= 3461.03) {
                    descInss = salBrt * 0.12;
                } else {
                    descInss = salBrt * 0.14;
                }
                if (salBrt <= 1903.98) {
                    descIr = 0;
                } else if (salBrt > 1903.98 && salBrt <= 2826.65) {
                    descIr = salBrt * 0.09;
                } else if (salBrt > 2826.65 && salBrt <= 3751.05) {
                    descIr = salBrt * 0.15;
                } else {
                    descIr = salBrt * 0.225;
                }
                double salLiq = ((salBrt - descInss) - descIr) + acrescFil;
                txtNome.setText( sexo + " " + nome );
                txtINSS.setText("O desconto de INSS foi: " + String.valueOf(f.format(descInss)));
                txtIR.setText("O desconto de IR foi: " + String.valueOf(f.format(descIr)));
                txtSalLiq.setText("O salário líquido será: " + String.valueOf(f.format(salLiq)));
            }
        });
    }
}