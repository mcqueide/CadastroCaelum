package br.com.caelum.task;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.dao.DAOHelper;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.converter.AlunoConverter;
import br.com.caelum.support.WebClient;

public class EnviaContatosTask extends AsyncTask<Object, Object, String> {

	private static final String ENDERECO = "http://www.caelum.com.br/mobile";
	private Context context;
	private ProgressDialog progress;

	public EnviaContatosTask(Context context) {
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(context, "Aguarde...",
				"Envio de dados para a web", true, true);
	}

	@Override
	protected String doInBackground(Object... params) {

		DAOHelper helper = new DAOHelper(context);
		AlunoDAO alunoDAO = new AlunoDAO(helper);

		List<Aluno> alunos = alunoDAO.getAlunos();

		String json = new AlunoConverter().toJSON(alunos);

		WebClient client = new WebClient(ENDERECO);

		String resposta = client.post(json);

		return resposta;
	}

	@Override
	protected void onPostExecute(String resposta) {
		progress.dismiss();
		Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();

	}

}
