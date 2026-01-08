import java.util.Scanner;

public class Sistema_Basico_Gestion_Productos {
    static Scanner sc = new Scanner(System.in);
    static String nombreProducto = "N/A";
    static double precioUnitario = -1;
    static int cantidadInventario = -1;
    static double valorTotal = -1;
    static String estado;

    public static void main(String[] args) throws Exception {
        boolean estaActivo = true;
        do {
            menuPrincipal();
            System.out.print("Ingrese su opción: ");
            int opción = sc.nextInt();
            sc.nextLine();
            System.out.println("");
            switch (opción) {
                case 1:
                    registrarNuevoProducto();
                    break;
                case 2:
                    mostrarInformacionProductoActual();
                    break;
                case 3:
                    mostrarValorTotalInventario();
                    break;
                case 4:
                    mostrarResumenCompleto();
                    break;
                case 5:
                    mostrarLimpiarDatos();
                    break;
                case 0:
                    estaActivo = !estaActivo;
                    System.out.println("\n======= Cerrando el Sistema =======");
                    break;
                default:
                    System.out.println("La opcion no es valida, intentelo de nuevo.");
            }
        } while (estaActivo == true);
    }

    // 1.Metodo para mostrar el menu principal
    static void menuPrincipal() {
        System.out.println("""
                ====== Sistema de Gestión de Productos ======

                1. Registrar nuevo producto
                2. Mostrar información del producto actual
                3. Calcular valor total del inventario
                4. Mostrar resumen completo del producto
                5. Limpiar datos del producto actual
                0. Salir

                        """);

    }

    // 2.Metodo para registrar la informacion de un producto
    static void registrarNuevoProducto() {
        if (nombreProducto.equalsIgnoreCase("N/A")) {
            registroProducto();
        } else {
            sobrescribirProducto();
        }
    }

    static void registroProducto() {
        System.out.print("Ingrese el Nombre de el Producto: ");
        nombreProducto = validarNombreProducto();
        System.out.print("Ingrese el Precio Unitario de el Producto: ");
        precioUnitario = validarPrecioUnitario();
        System.out.print("Ingrese la cantidad en Inventario:");
        cantidadInventario = validarCantidadInventario();
        sc.nextLine();
        System.out.println("");
    }

    static void sobrescribirProducto() {
        String remplazar;
        do {
            System.out.print("Desea sobrescribir el Producto (S/N): ");
            remplazar = sc.nextLine();
            if (remplazar.equalsIgnoreCase("s")) {
                registroProducto();
                return;
            } else if (remplazar.equalsIgnoreCase("n")) {
                System.out.println("Registro Cancelado\n");
                return;
            } else {
                System.out.println("Elija una opcion valida\n");
            }
        } while (!remplazar.equalsIgnoreCase("s") || !remplazar.equalsIgnoreCase("n"));
    }

    static String validarNombreProducto() {
        do {
            nombreProducto = sc.nextLine();
            if (!nombreProducto.trim().isEmpty()) {
                return nombreProducto;
            } else {
                System.out.println("El nombre de el Producto esta vacio o no es valido.");
            }
            System.out.print("Intente un nuevo nombre: ");
        } while (true);
    }

    static double validarPrecioUnitario() {
        do {
            double precio = sc.nextDouble();
            if (precio > 0) {
                return precio;
            } else {
                System.out.println("El precio no es valido, el precio es menor que 0\n");
                System.out.print("Ingrese un precio valido: ");
            }
        } while (true);
    }

    static int validarCantidadInventario() {
        while (true) {
            if (sc.hasNextInt()) {
                int cantidad = sc.nextInt();
                if (cantidad > 0) {
                    return cantidad;
                } else {
                    System.out.println("La cantidad debe ser mayor que cero");
                }
            } else {
                System.out.println("No ingresaste un numero entero, vuelve a intentarlo\n");
                sc.next();

            }
            System.out.print("Ingresa una cantidad valida: ");
        }
    }

    // 3.Metodos para mostrar informacion de el producto actual
    static void mostrarInformacionProductoActual() {
        if (nombreProducto.equalsIgnoreCase("N/A")) {
            System.out.println("No hay datos de producto registrados actualmente.\n");
        } else {
            System.out.println("===== Informacion del Producto =====\n");
            informacionProducto();
            System.out.println("\n");
        }
    }

    static void informacionProducto() {
        System.out.printf("Nombre del Producto: %s\nPrecio Unitario del Producto: %.2f\nCantidad en Inventario: %d",
                nombreProducto, precioUnitario, cantidadInventario);
    }

    // 4.Metodos para calcular el valor total de el inventario
    static void mostrarValorTotalInventario() {
        System.out.println("===== Valor Inventario =====");
        if (precioUnitario == -1) {
            System.out.println("\nNo hay productos registrados, registre un producto\n");
        } else {
            imprimirValorInventario();
            System.out.println("\n");
        }
    }

    static void imprimirValorInventario() {
        System.out.printf("%nEl Valor Total del Inventario es: %.2f", calcularValorTotal());
    }

    static double calcularValorTotal() {
        valorTotal = precioUnitario * cantidadInventario;
        return valorTotal;
    }

    // 5.Metodos para mostrar resumen completo del producto
    static void mostrarResumenCompleto() {
        if (nombreProducto.equalsIgnoreCase("N/A")) {
            System.out.println("No hay Productos registrados, registre un producto primero.\n");
        } else {
            System.out.println("===== Resumen del Producto =====\n");
            informacionProducto();
            imprimirValorInventario();
            System.out.println("\nEstado del Stock: " + estadoProducto() + "\n");
        }
    }

    static String estadoProducto() {
        if (cantidadInventario > 20) {
            estado = "Stock alto";
        } else if (cantidadInventario >= 5 && cantidadInventario <= 20) {
            estado = "Stock suficiente";
        } else {
            estado = "Stock bajo";
        }
        return estado;
    }

    // 6.Metodo Para limpiar Datos
    static void mostrarLimpiarDatos() {
        if (nombreProducto.equalsIgnoreCase("N/A")) {
            System.out.println("No hay Datos que limpiar, registre un producto.\n");
        } else {
            limpiarDatos();
            System.out.println("Datos limpiados correctamente.\n");
        }
    }

    static void limpiarDatos() {
        nombreProducto = "N/A";
        precioUnitario = -1;
        cantidadInventario = -1;
        valorTotal = -1;
    }

}
