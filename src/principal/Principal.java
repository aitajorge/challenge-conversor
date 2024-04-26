package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CotizaWeb consulta = CotizaWeb.buscaCotizacion("USD");

        if (consulta != null) {
            while (true) {
                System.out.println("*********************************************************");
                System.out.println("Sea bienvenido al Conversor de Moneda   =]");

                System.out.println("1. Dólar =>> Peso argentino");
                System.out.println("2. Peso argentino =>> Dólar");
                System.out.println("3. Dólar =>> Real brasileño");
                System.out.println("4. Real brasileño =>> Dólar");
                System.out.println("5. Dólar =>> Peso colombiano");
                System.out.println("6. Peso colombiano =>> Dólar");

                System.out.println("7. Salir");

                System.out.println("\nElija una opción válida: ");
                System.out.println("*********************************************************");
                int opcion;
                try{
                    opcion = scanner.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Error: Debe ingresar un número entero.");
                    scanner.nextLine();
                    continue;
                }

                if (opcion == 7) {
                    System.out.println("¡Hasta luego!");
                    break;
                }

                double tasaBase = 1.0;
                String monedaDestino = "";

                switch (opcion) {
                    case 1:
                    case 2:
                        monedaDestino = "ARS";
                        break;
                    case 3:
                    case 4:
                        monedaDestino = "BRL";
                        break;
                    case 5:
                    case 6:
                        monedaDestino = "COP";
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        continue;
                }

                tasaBase = consulta.conversion_rates().get(monedaDestino).getAsDouble();

                if (opcion == 1 || opcion == 3 || opcion == 5) {
                    System.out.println("Ingrese la cantidad en USD a convertir:");
                    double cantidadUSD = scanner.nextDouble();
                    double cantidadDestino = cantidadUSD * tasaBase;
                    System.out.println(cantidadUSD + " USD equivalen a " + cantidadDestino + " " + monedaDestino);
                } else {
                    System.out.println("Ingrese la cantidad en la moneda destino a convertir a USD:");
                    double cantidadDestino = scanner.nextDouble();
                    double cantidadUSD = cantidadDestino / tasaBase;
                    System.out.println(cantidadDestino + " " + monedaDestino + " equivalen a " + cantidadUSD + " USD");
                }
            }
        } else {
            System.out.println("No se pudo obtener la cotización para la moneda base especificada.");
        }

        scanner.close();
    }
}


