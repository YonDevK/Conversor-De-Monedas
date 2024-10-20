package conversordemonedas.model;


import java.time.LocalDateTime;

public class TasaCambio {
    private final Moneda monedaOrigen; // Moneda de la que se parte (ej: USD)
    private final Moneda monedaDestino; // Moneda a la que se quiere convertir (ej: ARS)
    private final double tasa; // Tasa de cambio entre las dos monedas
    private final LocalDateTime timestamp; // Fecha y hora en que se obtuvo la tasa

    // Constructor que toma la moneda de origen, la moneda destino y la tasa
    public TasaCambio(Moneda monedaOrigen, Moneda monedaDestino, double tasa) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tasa = tasa;
        this.timestamp = LocalDateTime.now(); // Guardar la fecha y hora actual
    }

    // Métodos para obtener las propiedades de la tasa de cambio
    public Moneda getMonedaOrigen() {
        return monedaOrigen;
    }

    public Moneda getMonedaDestino() {
        return monedaDestino;
    }

    public double getTasa() {
        return tasa;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Método que muestra la tasa de cambio como una cadena de texto
    @Override
    public String toString() {
        return String.format("1 %s = %.4f %s (fecha: %s)", monedaOrigen.obtenerCodigo(), tasa, monedaDestino.obtenerCodigo(), timestamp);
    }
}
