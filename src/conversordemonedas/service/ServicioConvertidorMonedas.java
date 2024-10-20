package conversordemonedas.service;


import java.util.HashMap;
import java.util.Map;

import conversordemonedas.api.ClienteTasaCambio;
import conversordemonedas.api.RespuestaTasaCambio;
import conversordemonedas.model.Moneda;
import conversordemonedas.model.TasaCambio;

public class ServicioConvertidorMonedas {
    private final ClienteTasaCambio cliente;
    private final Map<String, Moneda> monedas;
    private Map<String, TasaCambio> tasas; // Mapa para guardar las tasas de cambio

    // Constructor que inicializa el cliente de API y las monedas disponibles
    public ServicioConvertidorMonedas() {
        this.cliente = new ClienteTasaCambio();
        this.monedas = inicializarMonedas(); // Llena el mapa con las monedas soportadas
    }

    // Método para inicializar las monedas disponibles en el conversor
    private Map<String, Moneda> inicializarMonedas() {
        Map<String, Moneda> mapa = new HashMap<>();
        mapa.put("USD", new Moneda("USD", "Dólar Estadounidense"));
        mapa.put("ARS", new Moneda("ARS", "Peso Argentino"));
        mapa.put("BOB", new Moneda("BOB", "Boliviano"));
        mapa.put("BRL", new Moneda("BRL", "Real Brasileño"));
        mapa.put("CLP", new Moneda("CLP", "Peso Chileno"));
        mapa.put("COP", new Moneda("COP", "Peso Colombiano"));
        return mapa;
    }

    // Método para actualizar las tasas de cambio con datos de la API
    public void actualizarTasas() throws Exception {
        RespuestaTasaCambio respuesta = cliente.obtenerTasasRecientes();
        this.tasas = new HashMap<>();

        // Llena el mapa de tasas con los valores que vienen de la API
        for (Map.Entry<String, Double> entry : respuesta.obtenerTasas().entrySet()) {
            Moneda monedaOrigen = monedas.get("USD"); // El USD es la moneda base
            Moneda monedaDestino = monedas.get(entry.getKey());

            if (monedaOrigen != null && monedaDestino != null) {
                tasas.put(entry.getKey(), new TasaCambio(monedaOrigen, monedaDestino, entry.getValue()));
            }
        }
    }

    // Método que devuelve las tasas de cambio
    public Map<String, TasaCambio> getTasas() {
        return tasas;
    }

    // Método que devuelve las monedas disponibles
    public Map<String, Moneda> getMonedas() {
        return monedas;
    }

    // Método que convierte de una moneda a otra
    public double convertir(String codigoMonedaOrigen, String codigoMonedaDestino, double cantidad) {
        if (tasas == null) {
            throw new IllegalStateException("Las tasas de cambio no han sido inicializadas. Llama a actualizarTasas() primero.");
        }

        TasaCambio tasaOrigen = tasas.get(codigoMonedaOrigen);
        TasaCambio tasaDestino = tasas.get(codigoMonedaDestino);

        if (tasaOrigen == null || tasaDestino == null) {
            throw new IllegalArgumentException("Código de moneda no válido.");
        }

        return (cantidad / tasaOrigen.getTasa()) * tasaDestino.getTasa();
    }
}
