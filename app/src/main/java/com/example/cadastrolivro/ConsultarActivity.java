package com.example.cadastrolivro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {
    Button buttonNovo;
    ListView listViewFilmes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);

        associacao();

        SerieFilmeDao dao = new SerieFilmeDao(ConsultarActivity.this);
        ArrayList<SerieFilmeModel> arrayListModel = dao.consultarTodos();
        ArrayAdapter adapter = new ArrayAdapter(ConsultarActivity.this, android.R.layout.simple_list_item_1, arrayListModel);
        listViewFilmes.setAdapter(adapter);

        buttonNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void associacao() {
        buttonNovo = findViewById(R.id.buttonNovo);
        listViewFilmes = findViewById(R.id.listViewFilmes);
    }
}