package br.com.caelum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

public class DetalhesProvaFragment extends Fragment{

	private Prova prova;
	private TextView materia;
	private TextView data;
	private ListView topicos;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View inflate = inflater.inflate(R.layout.provas_detalhe, container,false);
		
		if  (getArguments() != null) {
            this.prova = (Prova) getArguments().getSerializable("prova");
        }

        if (this.prova != null) {
            buscaElementosView(inflate);
            populaElementosView(materia, data, topicos);
        }
		
		return inflate;
	}

	private void buscaElementosView(View inflate) {
		materia = (TextView) inflate
		        .findViewById(R.id.detalhe_prova_materia);
		data = (TextView) inflate
		        .findViewById(R.id.detalhe_prova_data);
		topicos = (ListView) inflate
		        .findViewById(R.id.detalhe_prova_topicos);
	}

	private void populaElementosView(TextView materia, TextView data,
			ListView topicos) {
		materia.setText(this.prova.getMateria());
		data.setText(this.prova.getData());

		ArrayAdapter<String> adapter = 
		    new ArrayAdapter<String>(
		        getActivity(), 
		        android.R.layout.simple_list_item_1,
		        prova.getTopicos());

		topicos.setAdapter(adapter);
	}
	
}
