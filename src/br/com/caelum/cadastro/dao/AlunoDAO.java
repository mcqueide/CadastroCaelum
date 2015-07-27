package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO {

	private DAOHelper helper;
	private static final String TABELA = "Alunos";

	public AlunoDAO(DAOHelper helper) {
		this.helper = helper;
	}

	public void insere(Aluno aluno) {
		ContentValues cv = toValues(aluno);

		helper.getWritableDatabase().insert(TABELA, null, cv);
	}
	
	public void deletar(Aluno aluno) {
		String args[] = {aluno.getId().toString()};
		helper.getWritableDatabase().delete(TABELA, "id=?",args);
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

	public List<Aluno> getAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String sql = "SELECT * FROM Alunos";
		Cursor cursor = helper.getReadableDatabase().rawQuery(sql, null);
		while (cursor.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
			aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
			aluno.setTelefone(cursor.getString(cursor
					.getColumnIndex("telefone")));
			aluno.setEndereco(cursor.getString(cursor
					.getColumnIndex("endereco")));
			aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
			aluno.setCaminhoFoto(cursor.getString(cursor
					.getColumnIndex("caminhoFoto")));
			aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));

			alunos.add(aluno);
		}

		cursor.close();

		return alunos;
	}

	public void alterar(Aluno aluno) {
		ContentValues cv = toValues(aluno);

		String[] args = {aluno.getId().toString()};
		helper.getWritableDatabase().update(TABELA, cv, "id=?", args);
	}
	
	public boolean isAluno(String telefone) {

	    Cursor rawQuery = helper.getReadableDatabase().rawQuery("SELECT telefone from " + TABELA
	            + " WHERE telefone = ?", new String[]{telefone});

	    int total = rawQuery.getCount();
	    rawQuery.close();

	    return total > 0;
	}
	
}
