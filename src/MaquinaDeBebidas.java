import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Simula una máquina expendedora que procesa pagos para bebidas seleccionadas,
 * aceptando solo billetes de 1, 2 o 5 dólares hasta que el precio de la bebida seleccionada sea alcanzado o superado.
 */
public class MaquinaDeBebidas {

    /**
     * Punto de entrada principal. Gestiona la selección de bebidas y el proceso de pago.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la máquina expendedora de bebidas");
        mostrarOpciones();

        int opcion = obtenerOpcionValida(scanner);

        if (procesarPago(scanner, opcion)) {
            entregarBebida(opcion);
            System.out.println("Disfrute su bebida.");
        } else {
            System.out.println("Transacción cancelada.");
        }

        scanner.close();
    }

    /**
     * Muestra las opciones de bebidas disponibles.
     */
    private static void mostrarOpciones() {
        System.out.println("Elige una opción de las siguientes:");
        System.out.println("0 - Café ($1.50)");
        System.out.println("1 - Mate ($1.20)");
        System.out.println("2 - Gaseosa ($2.00)");
        System.out.println("3 - Jugo ($1.75)");
        System.out.println("4 - Cerveza ($3.00)");
        System.out.println("5 - Vino ($2.50)");
    }

    /**
     * Valida la selección de bebida del usuario.
     * @param scanner El scanner para leer la entrada del usuario.
     * @return La selección validada.
     */
    private static int obtenerOpcionValida(Scanner scanner) {
        int opcion;
        while (true) {
            System.out.println("Ingrese un número del 0 al 5: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 0 && opcion <= 5) {
                    return opcion;
                }
            } else {
                scanner.next(); // descartar entrada no válida
            }
            System.out.println("Entrada no válida. Por favor, ingrese un número entre 0 y 5.");
        }
    }

    /**
     * Procesa el pago de la bebida seleccionada.
     * @param scanner El scanner para leer la entrada de dinero del usuario.
     * @param opcion La opción de bebida seleccionada por el usuario.
     * @return true si el pago es suficiente; false en caso contrario.
     */
    private static boolean procesarPago(Scanner scanner, int opcion) {
        double[] precios = {1.50, 1.20, 2.00, 1.75, 3.00, 2.50};
        double precio = precios[opcion];
        System.out.println("La máquina acepta billetes de $1, $2 y $5.");
        System.out.printf("El precio de la bebida es $%.2f. Por favor, introduzca el dinero:\n", precio);

        double acumulado = 0.0;
        while (acumulado < precio) {
            try {
                double billete = scanner.nextDouble();
                if (billete == 1 || billete == 2 || billete == 5) {
                    acumulado += billete;
                    System.out.printf("Has introducido $%.2f. Total acumulado: $%.2f.\n", billete, acumulado);
                    if (acumulado >= precio) {
                        System.out.printf("Pago completo. Cambio devuelto: $%.2f\n", acumulado - precio);
                        return true;
                    }
                } else {
                    System.out.println("Billete no válido. Por favor, introduzca billetes de $1, $2 o $5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, introduzca un billete válido de $1, $2 o $5.");
                scanner.next(); // Limpiar buffer
            }
        }
        return false;
    }

    /**
     * Entrega la bebida seleccionada al usuario.
     * @param opcion La opción de bebida seleccionada.
     */
    private static void entregarBebida(int opcion) {
        switch (opcion) {
            case 0:
                System.out.println("Café, buenísima elección, aun mejor si es café Colombiano.");
                break;
            case 1:
                System.out.println("Mate, probablemente puedes ser argentino, uruguayo o paraguayo, buena elección.");
                break;
            case 2:
                System.out.println("Gaseosa, ten cuidado con el azúcar porque es mala para la salud.");
                break;
            case 3:
                System.out.println("Jugo, ten cuidado con el azúcar no son jugos naturales.");
                break;
            case 4:
                System.out.println("Cerveza, si tomaste cerveza, no manejes por favor.");
                break;
            case 5:
                System.out.println("Vino, si tomaste vino, no manejes por favor.");
                break;
            default:
                System.out.println("Opción no válida. Fin del programa.");
                return;
        }
    }
}

