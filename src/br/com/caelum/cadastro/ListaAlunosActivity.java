package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listagem);
		
		ListView lista = (ListView)findViewById(R.id.lista);
		
		String[] alunos = {"Suelen","Guilherme","Mauricio"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
		
		lista.setAdapter(adapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Toast.makeText(ListaAlunosActivity.this, "A  posição é" + posicao, Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
}
