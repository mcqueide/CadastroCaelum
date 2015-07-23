package br.com.caelum.cadastro;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;
import br.com.caelum.cadastro.modelo.Aluno;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends Activity {

	private FormularioHelper helper;
	private AlunoDAO alunoDAO;
	private DAOHelper dao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		this.helper = new FormularioHelper(this);
		
		Button botao = (Button)findViewById(R.id.botao);
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				dao = new DAOHelper(FormularioActivity.this);
				alunoDAO = new AlunoDAO(dao);
				alunoDAO.insere(aluno);
				Toast.makeText(FormularioActivity.this,"Objeto aluno criado" + aluno.getNome(), Toast.LENGTH_SHORT).show();
				finish();
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
	protected void onDestroy() {
		super.onDestroy();
		dao.close();
	}
}
