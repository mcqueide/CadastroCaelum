package br.com.caelum.cadastro.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOHelper extends SQLiteOpenHelper{

	private static final int VERSAO = 4;
	private static final String DATABASE = "CadastroCaelum";

	public DAOHelper(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String tabelaAlunos = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, "
				+ "nome TEXT UNIQUE NOT NULL, "
				+ "telefone TEXT, endereco TEXT, "
				+ "site TEXT, nota REAL, "
				+ "caminhoFoto TEXT);";
		db.execSQL(tabelaAlunos);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		String sql = "DROP TABLE IF EXISTS alunos;";
		db.execSQL(sql);
		onCreate(db);
	}

}
