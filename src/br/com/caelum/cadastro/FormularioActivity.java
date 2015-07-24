package br.com.caelum.cadastro;

import java.io.Serializable;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;
import br.com.caelum.cadastro.extra.Extra;
import br.com.caelum.cadastro.modelo.Aluno;
import android.app.Activity;
import android.content.Intent;
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
	private Aluno aluno;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		aluno = (Aluno) getIntent().
				getSerializableExtra(Extra.ALUNO_SELECIONADO);
		
		dao = new DAOHelper(FormularioActivity.this);
		this.helper = new FormularioHelper(this);
		
		final Button botao = (Button)findViewById(R.id.botao);
		
		if(aluno!= null){
			botao.setText("Alterar");
			helper.colocaNoFormulario(aluno);
		}
		
		
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				aluno = helper.pegaAlunoDoFormulario();
				
				alunoDAO = new AlunoDAO(dao);
				
				if (aluno.getId()!= null) {
					alunoDAO.alterar(aluno);
				}else{
					alunoDAO.insere(aluno);
				}
				
				Toast.makeText(FormularioActivity.this,"Objeto aluno criado " + aluno.getNome(), Toast.LENGTH_SHORT).show();
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
