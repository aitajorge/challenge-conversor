package principal;

import com.google.gson.JsonObject;

public record CotizaWeb(String base_code,
                        JsonObject conversion_rates) {
}
