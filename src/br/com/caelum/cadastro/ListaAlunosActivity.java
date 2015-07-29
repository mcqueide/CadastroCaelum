package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import br.com.caelum.cadastro.adapter.AlunoAdapter;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;
import br.com.caelum.cadastro.extra.Extra;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.task.EnviaContatosTask;

public class ListaAlunosActivity extends Activity {

	private DAOHelper dao;
	private AlunoDAO alunoDAO;
	private ListView lista;
	private Aluno alunoSelecionado;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listagem);

		lista = (ListView) findViewById(R.id.lista);
		registerForContextMenu(lista);

		dao = new DAOHelper(this);
		alunoDAO = new AlunoDAO(dao);

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				
				alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
				Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
				edicao.putExtra(Extra.ALUNO_SELECIONADO, alunoSelecionado);
				
				startActivity(edicao);
			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {

				alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
				
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
			Intent irParaFormulario = new Intent(this, FormularioActivity.class);
			startActivity(irParaFormulario);
			return false;
			
		case R.id.menu_enviar_alunos:
			new EnviaContatosTask(this).execute();
			return false;
            
		case R.id.menu_receber_provas:
			Intent irParaProvas = new Intent(this,ProvasActivity.class);
			startActivity(irParaProvas);
			return false;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		List<Aluno> alunos = alunoDAO.getAlunos();

		AlunoAdapter adapter = new AlunoAdapter(this,alunos);

		lista.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dao.close();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuItem ligar = menu.add("Ligar");
		executarMenu(ligar, new Intent(Intent.ACTION_CALL), Uri.parse("tel:"+alunoSelecionado.getTelefone()));
			
			
		MenuItem enviar = menu.add("Enviar SMS");
		executarMenu(enviar, new Intent(Intent.ACTION_VIEW), Uri.parse("sms:"+alunoSelecionado.getTelefone()));
		
		MenuItem mapa = menu.add("Achar no Mapa");
		executarMenu(mapa, new Intent(Intent.ACTION_VIEW), Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()));
		
		MenuItem site = menu.add("Navegar no site");
		String http = alunoSelecionado.getSite().startsWith("http://") ? "" : "http://";
		executarMenu(site, new Intent(Intent.ACTION_VIEW), Uri.parse(http+alunoSelecionado.getSite()));
		
		
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				alunoDAO.deletar(alunoSelecionado);
				carregaLista();
				return false;
			}
		});
		
		MenuItem email = menu.add("Enviar E-mail");
		Intent intentEmail = new Intent(Intent.ACTION_SEND);
		intentEmail.setType("message/rtc822");
	    intentEmail.putExtra(Intent.EXTRA_EMAIL, new String[] {"mcqueide@gmail.com"});
	    intentEmail.putExtra(Intent.EXTRA_SUBJECT, "Testando subject do email");
	    intentEmail.putExtra(Intent.EXTRA_TEXT, "Testando corpo do email");
	    email.setIntent(intentEmail);
	}
	
	private void executarMenu(MenuItem menu,Intent intent,Uri parse){
		intent.setData(parse);
		menu.setIntent(intent);
	}

}
