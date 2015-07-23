package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listagem);
		
		final ListView lista = (ListView)findViewById(R.id.lista);
		
		final String[] alunos = {"Suelen","Guilherme","Mauricio"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
		
		lista.setAdapter(adapter);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Toast.makeText(ListaAlunosActivity.this, "A  posição é " + posicao, Toast.LENGTH_SHORT).show();
				
			}
		});
		
		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Toast.makeText(
						ListaAlunosActivity.this,
						"Aluno clicado é: " + lista.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_novo:
			Intent irParaFormulario = new Intent(this,FormularioActivity.class);
			startActivity(irParaFormulario);
			return false;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
}
