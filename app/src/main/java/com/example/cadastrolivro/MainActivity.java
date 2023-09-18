package com.example.cadastrolivro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editTextNome;
    Spinner spinnerGenero;
    RadioButton radioButtonFilme, radioButtonSerie;
    RatingBar ratingBarNota;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        associaJavaXml();
        carregarGeneros();

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validado = validarFormulario();
                if(validado) {
                    SerieFilmeDao dao = new SerieFilmeDao(MainActivity.this);
                    SerieFilmeModel model = new SerieFilmeModel();
                    model.nome = editTextNome.getText().toString();
                    model.genero = spinnerGenero.toString();
                    model.nota = ratingBarNota.getRating();
                    model.tipo = (radioButtonFilme.isChecked() ? "filme" : "série");

                    dao.inserir(model);
                    Toast.makeText(MainActivity.this, "Cadastrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ConsultarActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void carregarGeneros() {
        ArrayList vetorGeneros = new ArrayList();
        vetorGeneros.add("Selecione o gênero");
        vetorGeneros.add("Ação");
        vetorGeneros.add("Drama");
        vetorGeneros.add("Sci-fi");
        vetorGeneros.add("Comédia");
        vetorGeneros.add("Terror");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item, vetorGeneros );
        spinnerGenero.setAdapter(adapter);
    }

    private boolean validarFormulario() {
        String nomeFilme = editTextNome.getText().toString();
        if(nomeFilme.trim().length() < 2){
            editTextNome.setError("mínimo 2 letras");
            return false;
        }
        else if(spinnerGenero.getSelectedItemPosition() == 0){
            Toast.makeText(this, "selecione um gênero", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!radioButtonSerie.isChecked() && !radioButtonFilme.isChecked()){
            Toast.makeText(this, "selecione um tipo", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @SuppressLint("WrongViewCast")
    private void associaJavaXml() {
        editTextNome = findViewById(R.id.editTextNomeFilme);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        radioButtonFilme = findViewById(R.id.radioFilme);
        radioButtonSerie = findViewById(R.id.radioSerie);
        ratingBarNota = findViewById(R.id.ratingNota);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
    }
}