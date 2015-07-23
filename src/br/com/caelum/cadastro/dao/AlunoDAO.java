package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO{

	private DAOHelper helper;
	private static final String TABELA = "Alunos";
	
	public AlunoDAO(DAOHelper helper) {
        this.helper = helper;
    }

	public void insere(Aluno aluno) {
		ContentValues cv = toValues(aluno);
		
		helper.getWritableDatabase().insert(TABELA, null, cv);
	}

	private ContentValues toValues(Aluno aluno) {
		ContentValues cv = new ContentValues();
		cv.put("nome", aluno.getNome());
		cv.put("telefone", aluno.getTelefone());
		cv.put("endereco", aluno.getEndereco());
		cv.put("site", aluno.getSite());
		cv.put("nota", aluno.getNota());
		cv.put("caminhoFoto", aluno.getCaminhoFoto());
		return cv;
	}
	
	public DAOHelper getHelper() {
		return helper;
	}

}
