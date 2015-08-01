package br.com.caelum.fragment;

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.util.Localizador;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment {

	@Override
	public void onResume() {
		super.onResume();

		Localizador localizador = new Localizador(getActivity());
		LatLng local = localizador
				.getCoordenada("Rua Vergueiro 3185 Vila Mariana");

		centralizaNo(local);
		
		AlunoDAO dao = new AlunoDAO(getActivity());
	    List<Aluno> alunos = dao.getLista();
	    dao.close();

	    for (Aluno aluno : alunos) {
	        Localizador localizadorAluno = new Localizador(getActivity());

	        LatLng coordenada = localizadorAluno.getCoordenada(aluno.getEndereco());

	        if (coordenada != null) {
	            MarkerOptions marcador = new MarkerOptions().position(coordenada)
	              .title(aluno.getNome()).snippet(aluno.getEndereco());

	            getMap().addMarker(marcador);
	        }
	    }

	}

	private void centralizaNo(LatLng local) {
		GoogleMap mapa = this.getMap();
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
	}

}
