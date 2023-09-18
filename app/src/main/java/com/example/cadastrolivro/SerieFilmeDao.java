package com.example.cadastrolivro;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//dao: data access objeto: classe que acessa o db
public class SerieFilmeDao extends SQLiteOpenHelper {
    private final String TABELA = "tb_serie_filme";
    public SerieFilmeDao(Context context) {
        //aqui o valor "1" indica a versao do db
        super(context, "db_serie_filme", null, 1);
    }

    //chamado somente quando o db é criado
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String comando = "create table " + TABELA + "(" +
                "id integer primary key," +
                "nome varchar(200) not null," +
                "genero varchar(50)," +
                "tipo varchar(10) not null," +
                "nota decimal(3,1)" +
                ")";

        sqLiteDatabase.execSQL(comando);
    }

    //chamado quando a versao do db atual é maior que a anterior
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //alter table.....
    }

    public void inserir(SerieFilmeModel model){
        ContentValues values = new ContentValues();
        values.put("nome", model.nome);
        values.put("genero", model.genero);
        values.put("tipo", model.tipo);
        values.put("nota", model.nota);

        getWritableDatabase().insertOrThrow(TABELA, null, values);
    }
    public ArrayList<SerieFilmeModel> consultarTodos(){
        String comando = "select * from " + TABELA;
        Cursor cursor = getReadableDatabase().rawQuery(comando, null);
        ArrayList<SerieFilmeModel> arrayListModel = new ArrayList<>();
        while(cursor.moveToNext()){
            SerieFilmeModel model = new SerieFilmeModel();
            model.id = cursor.getLong(0);
            model.nome = cursor.getString(1);
            model.genero = cursor.getString(2);
            model.nota = cursor.getDouble(3);
            arrayListModel.add(model);
        }

        return arrayListModel;
    }
}