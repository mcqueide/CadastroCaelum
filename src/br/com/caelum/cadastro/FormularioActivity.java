package br.com.caelum.cadastro;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;
import br.com.caelum.cadastro.extra.Extra;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {

	private static final int RESULT_CAMERA = 123;
	private FormularioHelper helper;
	private AlunoDAO alunoDAO;
	private DAOHelper dao;
	private Aluno aluno;
	private String caminhoArquivo;

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
		
		ImageView foto = helper.getFoto();
        foto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                
                caminhoArquivo = getExternalFilesDir(null) +"/"+ System.currentTimeMillis()+".png";
                
                Uri localFoto = Uri.fromFile(new File(caminhoArquivo));
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
                
                startActivityForResult(irParaCamera, RESULT_CAMERA);
            }
        });
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode==RESULT_CAMERA){
			if(resultCode == Activity.RESULT_OK){
				helper.carregaImagem(caminhoArquivo);
            } else {
                caminhoArquivo = null;
            }
		}
		
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
