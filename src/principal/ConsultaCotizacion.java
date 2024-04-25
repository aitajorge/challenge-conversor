package principal;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    public CotizaWeb buscaCotizacion(String monedaCotizacion){


    URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bd677ae8782ac9e96e1fa928/latest/"+monedaCotizacion);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(direccion)
            .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | RuntimeException  e) {
            throw new RuntimeException();

        }


        return new Gson().fromJson(response.body(), CotizaWeb.class);
}
