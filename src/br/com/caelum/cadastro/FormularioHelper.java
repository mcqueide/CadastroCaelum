package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

		nome = (EditText) formularioActivity.findViewById(R.id.nome);
		telefone = (EditText) formularioActivity.findViewById(R.id.telefone);
		endereco = (EditText) formularioActivity.findViewById(R.id.endereco);
		site = (EditText) formularioActivity.findViewById(R.id.site);
		nota = (SeekBar) formularioActivity.findViewById(R.id.nota);
		foto = (ImageView) formularioActivity.findViewById(R.id.foto);

		aluno = new Aluno();
	}

	public Aluno pegaAlunoDoFormulario() {
		
		aluno.setNome(nome.getEditableText().toString());
		aluno.setTelefone(telefone.getEditableText().toString());
		aluno.setEndereco(endereco.getEditableText().toString());
		aluno.setSite(site.getEditableText().toString());
		aluno.setNota(Double.valueOf(nota.getProgress()));
		return aluno;
	}

	public void colocaNoFormulario(Aluno aluno) {
		
		nome.setText(aluno.getNome());
		telefone.setText(aluno.getTelefone());
		endereco.setText(aluno.getEndereco());
		site.setText(aluno.getSite());
		nota.setProgress(aluno.getNota().intValue());
		
		this.aluno = aluno;
		
		if (aluno.getCaminhoFoto()!=null) {
			carregaImagem(aluno.getCaminhoFoto());
		}
	}
	
	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String caminhoArquivo) {
		aluno.setCaminhoFoto(caminhoArquivo);
		
		Bitmap imagemFoto = BitmapFactory.decodeFile(caminhoArquivo);
		Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
		
		foto.setImageBitmap(imagemFotoReduzida);
		
	}

}
