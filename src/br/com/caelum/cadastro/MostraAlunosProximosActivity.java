package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import br.com.caelum.fragment.MapaFragment;

public class MostraAlunosProximosActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.map_layout);
		
		MapaFragment mapaFragment = new MapaFragment();
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		
		transaction.replace(R.id.mapa, mapaFragment);
		transaction.commit();
	}
	
}
