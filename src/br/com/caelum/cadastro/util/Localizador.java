package br.com.caelum.cadastro.util;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

public class Localizador {

	private Geocoder geocoder;

	public Localizador(Context context) {
		geocoder = new Geocoder(context);
	}

	public LatLng getCoordenada(String endereco) {

		try {

			List<Address> enderecos = geocoder.getFromLocationName(endereco, 1);

			if (!enderecos.isEmpty()) {
				Address address = enderecos.get(0);
				return new LatLng(address.getLatitude(), address.getLongitude());
			} else {
				return null;
			}
		} catch (IOException e) {
			return null;
		}

	}

}
