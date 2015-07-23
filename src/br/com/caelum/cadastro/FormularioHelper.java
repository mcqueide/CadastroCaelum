package br.com.caelum.cadastro;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioHelper {

	private Aluno aluno;
	private ImageView foto;
	private EditText nome;
	private EditText telefone;
	private EditText endereco;
	private EditText site;
	private SeekBar nota;

	public FormularioHelper(FormularioActivity formularioActivity) {

		foto = (ImageView) formularioActivity.findViewById(R.id.foto);
		nome = (EditText) formularioActivity.findViewById(R.id.nome);
		telefone = (EditText) formularioActivity.findViewById(R.id.telefone);
		endereco = (EditText) formularioActivity.findViewById(R.id.endereco);
		site = (EditText) formularioActivity.findViewById(R.id.site);
		nota = (SeekBar) formularioActivity.findViewById(R.id.nota);

		aluno = new Aluno();
	}

	public Aluno pegaAlunoDoFormulario() {
		aluno.setNome(nome.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setNota(nota.getProgress());
		return aluno;
	}

}