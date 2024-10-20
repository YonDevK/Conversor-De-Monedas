package conversordemonedas.ui;

import java.util.Scanner;

import conversordemonedas.model.Moneda;
import conversordemonedas.service.ServicioConvertidorMonedas;

public class ConsoleUI {
    private final ServicioConvertidorMonedas servicio;  // Servicio para gestionar conversiones
    private final Scanner scanner;  // Escáner para recibir entradas del usuario

    /**
     * Constructor de la clase ConsoleUI.
     * Inicializa el servicio de conversión y el escáner para la entrada del usuario.
     *
     * @param servicio El servicio que proporciona las conversiones de monedas.
     */
    public ConsoleUI(ServicioConvertidorMonedas servicio) {
        this.servicio = servicio;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método que inicia la interfaz de usuario en la consola.
     * Muestra un menú y maneja las interacciones con el usuario para realizar conversiones de monedas.
     */
    public void iniciar() {
        try {
            // Actualizar las tasas de cambio antes de comenzar
            servicio.actualizarTasas();
            while (true) {
                // Mostrar el menú
                mostrarMenu();
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                // Salir si la opción es 7
                if (opcion == 7) {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                    break;
                }

                // Manejar la opción elegida
                manejarOpcion(opcion);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método que muestra el menú de opciones en la consola.
     * Permite al usuario elegir una opción para realizar una conversión de moneda.
     */
    private void mostrarMenu() {
        System.out.println("\n--- Conversor de Monedas ---");
        System.out.println("1. ARS a USD");
        System.out.println("2. BOB a USD");
        System.out.println("3. BRL a USD");
        System.out.println("4. CLP a USD");
        System.out.println("5. COP a USD");
        System.out.println("6. USD a otra moneda");
        System.out.println("7. Salir");
        System.out.print("Elija una opción: ");
    }

    /**
     * Método que maneja la opción seleccionada por el usuario.
     * Realiza la conversión de la moneda seleccionada a USD o de USD a otra moneda, según la elección del usuario.
     *
     * @param opcion La opción elegida por el usuario en el menú.
     */
    private void manejarOpcion(int opcion) {
        String codigoMonedaOrigen;
        String codigoMonedaDestino = "USD";  // Por defecto, convertir a USD

        // Determinar la moneda de origen según la opción elegida
        switch (opcion) {
            case 1:
                codigoMonedaOrigen = "ARS";
                break;
            case 2:
                codigoMonedaOrigen = "BOB";
                break;
            case 3:
                codigoMonedaOrigen = "BRL";
                break;
            case 4:
                codigoMonedaOrigen = "CLP";
                break;
            case 5:
                codigoMonedaOrigen = "COP";
                break;
            case 6:
                codigoMonedaOrigen = "USD";
                System.out.print("Ingrese la moneda de destino (ARS, BOB, BRL, CLP, COP): ");
                codigoMonedaDestino = scanner.nextLine().toUpperCase();  // Leer y convertir a mayúsculas
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        // Solicitar la cantidad a convertir
        System.out.print("Ingrese la cantidad a convertir: ");
        double cantidad = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea

        // Realizar la conversión y mostrar el resultado
        try {
            double resultado = servicio.convertir(codigoMonedaOrigen, codigoMonedaDestino, cantidad);
            Moneda monedaOrigen = servicio.getMonedas().get(codigoMonedaOrigen);
            Moneda monedaDestino = servicio.getMonedas().get(codigoMonedaDestino);

            // Mostrar el resultado de la conversión en formato legible
            System.out.printf("%.2f %s = %.2f %s%n", cantidad, monedaOrigen.obtenerCodigo(), resultado, monedaDestino.obtenerCodigo());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

