package br.com.caelum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.caelum.cadastro.R;

public class DetalhesProvaFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View inflate = inflater.inflate(R.layout.provas_detalhe, container,false);
		
		
		return inflate;
	}
	
}
