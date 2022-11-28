package fidelity.clase6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import fidelity.clase6.model.Cancion;
import fidelity.clase6.model.Usuario;
import fidelity.clase6.repositories.CancionRepository;


@SpringBootTest
class Clase6ApplicationTests {

	private String url = "http://localhost:8080/API/";

	//@Autowired
    //private CancionRepository cancionRepository;

	//@Autowired
	//MockMvc mock;

	@Test
	void contextLoads() {
		/*Cancion cancion = new Cancion("nombre", 54);
        cancionRepository.save(cancion);
        Optional<Cancion> cancionBuscada = cancionRepository.findById(1);
		assertEquals(cancionBuscada, cancion);*/
        //assertThat(buscarCancion.getNombre()).isEqualTo(cancion.getNombre());

	}

	@Test
	void llamada() {

		// get
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url + "getId");
		Invocation.Builder request = target.request();

		request.buildGet();

		// import java.net.http.HttpRequest;
		/*HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
		uri(URI.create("http://cominio.com:8080/API/")).GET().headers("token", "123").build();

		try {
			HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
			System.out.println(response.statusCode() + "\n" +
			response.body() + "\n" + response.headers());
			
			//HttpHeaders headers;
			//headers = response.headers();
			//headers
		} catch(IOException | InterruptedException e) {
			System.out.println("error en el request");
		}*/
	}

	@Test
	void post1(Usuario user) {

		// post
		Client cliente = ClientBuilder.newClient();
		WebTarget target = cliente.target(url + "getId");
		Invocation.Builder request = target.request();

		Gson gson = new Gson();
		String json = gson.toJson(user);

		Response response = request.post(Entity.json(json));
		String responseJson = response.readEntity(String.class);
		int status = response.getStatus();
	}
}
