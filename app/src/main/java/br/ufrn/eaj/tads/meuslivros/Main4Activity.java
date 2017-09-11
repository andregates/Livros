package br.ufrn.eaj.tads.meuslivros;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.ufrn.eaj.tads.meuslivros.model.BancoHelper;
import br.ufrn.eaj.tads.meuslivros.model.Livro;

public class Main4Activity extends AppCompatActivity {

    private TextView titulo;
    private TextView autor;
    private TextView ano;
    private TextView nota;
    private List<Livro> lista;
    private int iAtual=-1;
    ArrayAdapter<String> adaptador;
    private String[] LIVROS;
    private BancoHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        titulo = (TextView) findViewById(R.id.titulo);
        autor = (TextView) findViewById(R.id.autor);
        ano = (TextView) findViewById(R.id.ano);
        nota = (TextView) findViewById(R.id.nota);

        db = new BancoHelper(this);

        lista = db.findAll();

        LIVROS = new String[lista.size()];


        int i = 0;
        for (Livro l: lista){
            LIVROS[i] = l.getTitulo();
            i++;
        }


        AutoCompleteTextView autoCompleteLivros = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LIVROS);
        autoCompleteLivros.setAdapter(adaptador);

        autoCompleteLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Livro liv = db.findByTitulo(adaptador.getItem(i).toString());

                if(liv!=null) {


                    titulo.setText(liv.getTitulo());
                    autor.setText(liv.getAutor());
                    ano.setText(String.valueOf(liv.getAno()));
                    nota.setText(String.valueOf(liv.getNota()));
                }

            }
        });



    }


    @Override
    protected void onPause() {
        super.onPause();

    }
}
