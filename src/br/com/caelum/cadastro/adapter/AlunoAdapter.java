package br.com.caelum.cadastro.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoAdapter extends BaseAdapter {

	private final List<Aluno> alunos;
	private final Activity activity;

	public AlunoAdapter(Activity activity, List<Aluno> alunos) {
		this.activity = activity;
		this.alunos = alunos;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Aluno aluno = alunos.get(position);

		LayoutInflater inflater = activity.getLayoutInflater();
		View linha = inflater.inflate(R.layout.item, null);

		TextView nome = (TextView) linha.findViewById(R.id.nome);
		nome.setText(aluno.getNome());

		Bitmap bm;

		if (aluno.getCaminhoFoto() != null) {
			bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
		} else {
			bm = BitmapFactory.decodeResource(activity.getResources(),
					R.drawable.ic_no_image);
		}

		bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
		
		ImageView foto = (ImageView)linha.findViewById(R.id.foto);
		foto.setImageBitmap(bm);
		
		if (position % 2 == 0) {
		    linha.setBackgroundColor(activity.getResources().
		        getColor(R.color.linha_par));
		}

		return linha;
	}

}
