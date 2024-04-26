// En CotizaWeb.java
package principal;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public record CotizaWeb(String base_code, JsonObject conversion_rates) {
    public static CotizaWeb buscaCotizacion(String monedaCotizacion) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/bd677ae8782ac9e96e1fa928/latest/" + monedaCotizacion);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            String baseCode = jsonResponse.get("base_code").getAsString();
            JsonObject conversionRates = jsonResponse.getAsJsonObject("conversion_rates");
            return new CotizaWeb(baseCode, conversionRates);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Error en la solicitud HTTP");
        }
    }
}

