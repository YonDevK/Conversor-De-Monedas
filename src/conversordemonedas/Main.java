package conversordemonedas;

import conversordemonedas.service.ServicioConvertidorMonedas;
import conversordemonedas.ui.ConsoleUI;

//Clase principal que inicia la aplicación
public class Main {
 // Método principal (main) que es el punto de entrada de la aplicación
 public static void main(String[] args) {
     // Crear una instancia del servicio de conversión de monedas
     ServicioConvertidorMonedas servicio = new ServicioConvertidorMonedas();

     // Crear la interfaz de usuario de consola y pasarle el servicio
     // Esto conecta la lógica de conversión con la interfaz que verá el usuario
     ConsoleUI ui = new ConsoleUI(servicio);

     // Iniciar la interfaz de usuario
     // Esto comienza el programa y permite al usuario interactuar con el conversor de monedas
     ui.iniciar();
 }
}