package conversordemonedas.api;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class RespuestaTasaCambio {
    // Mapa que guarda las tasas de cambio para las monedas
    private final Map<String, Double> tasas;

    // Constructor que toma la respuesta de la API y llena el mapa con las tasas
    public RespuestaTasaCambio(JsonObject jsonRespuesta) {
        this.tasas = new HashMap<>();
        JsonObject tasasDeConversion = jsonRespuesta.getAsJsonObject("conversion_rates");

        // Agregar las tasas de cambio para cada moneda específica
        for (String moneda : new String[]{"ARS", "BOB", "BRL", "CLP", "COP", "HNL", "USD"}) {
            if (tasasDeConversion.has(moneda)) {
                tasas.put(moneda, tasasDeConversion.get(moneda).getAsDouble());
            }
        }
    }

    // Método que devuelve las tasas de cambio como un mapa
    public Map<String, Double> obtenerTasas() {
        return tasas;
    }
}
