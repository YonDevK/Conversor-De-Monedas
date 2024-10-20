package conversordemonedas.model;

public class Moneda {
    private final String codigo; // Código de la moneda (como "USD" o "ARS")
    private final String nombre; // Nombre de la moneda (como "Dólar Estadounidense")

    // Constructor que toma el código y nombre de la moneda
    public Moneda(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    // Método que devuelve el código de la moneda
    public String obtenerCodigo() {
        return codigo;
    }

    // Método que devuelve el nombre de la moneda
    public String obtenerNombre() {
        return nombre;
    }

    // Método que muestra la moneda como una cadena de texto
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}
