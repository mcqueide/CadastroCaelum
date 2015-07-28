package br.com.caelum.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {

	private final String url;

	public WebClient(String url) {
		this.url = url;
	}

	public String post(String json) {

		DefaultHttpClient httpClient = new DefaultHttpClient();

		// aqui escolha a uri passada pelo construtor:
		try {
			HttpPost post = new HttpPost("http://www.meuservidor.com.br");
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json");

			post.setEntity(new StringEntity(json));

			HttpResponse response = httpClient.execute(post);
			String jsonDeResposta = EntityUtils.toString(response.getEntity());

			return jsonDeResposta;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}