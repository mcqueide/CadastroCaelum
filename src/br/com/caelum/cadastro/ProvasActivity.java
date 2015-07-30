package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.cadastro.modelo.Prova;
import br.com.caelum.fragment.DetalhesProvaFragment;
import br.com.caelum.fragment.ListaProvasFragment;

public class ProvasActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

		setContentView(R.layout.provas);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		if (isTablet()) {
			transaction.replace(R.id.provas_lista, new ListaProvasFragment());
			transaction.replace(R.id.provas_detalhe,
					new DetalhesProvaFragment());

		} else {
			transaction.replace(R.id.provas_view, new ListaProvasFragment());
		}
		transaction.commit();
	}

	public boolean isTablet() {
		return getResources().getBoolean(R.bool.isTablet);
	}

	public void selecionaProva(Prova selecionada) {

		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", selecionada);

		DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
		detalhesProva.setArguments(argumentos);

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.provas_view, detalhesProva);

		if (!isTablet()) {
			transaction.addToBackStack(null);
		}
		transaction.commit();

	}

}
