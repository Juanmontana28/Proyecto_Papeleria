package papeleria;

import java.util.Scanner;

public class Papeleria {
    // Atributos de control de consola privados
    private Inventario inventario;
    private Scanner lector;

    // Constructor que inicializa los componentes del sistema
    public Papeleria() {
        this.inventario = new Inventario();
        this.lector = new Scanner(System.in);
    }

    // Método controlador del flujo de la aplicación
    public void iniciar() {
        int opcion = -1;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");

            if (lector.hasNextInt()) {
                opcion = lector.nextInt();
                lector.nextLine(); // LIMPIEZA CLAVE DEL BÚFER: consume el '\n' sobrante de nextInt()
                procesarOpcion(opcion);
            } else {
                System.out.println("Error: Por favor ingrese un número entero válido.");
                lector.nextLine(); // Limpiar entrada incorrecta de texto en el búfer
            }
        } while (opcion != 6);
    }

    // Imprime la interfaz visual en la consola
    private void mostrarMenu() {
        System.out.println("\n=================================");
        System.out.println("      SISTEMA JH PAPELERÍA       ");
        System.out.println("=================================");
        System.out.println("1. Registrar nuevo producto");
        System.out.println("2. Consultar inventario completo");
        System.out.println("3. Buscar producto por código");
        System.out.println("4. Modificar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Salir del sistema");
        System.out.println("=================================");
    }

    // Direcciona el menú según la opción escogida
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("\n--- REGISTRAR PRODUCTO ---");
                System.out.print("Ingrese código del artículo: ");
                String cod = lector.nextLine();
                System.out.print("Ingrese nombre del artículo: ");
                String nom = lector.nextLine();

                System.out.print("Ingrese precio unitario ($): ");
                double pre = lector.nextDouble();
                lector.nextLine(); // LIMPIEZA CLAVE DEL BÚFER después de un double

                System.out.print("Ingrese cantidad en stock: ");
                int cant = lector.nextInt();
                lector.nextLine(); // LIMPIEZA CLAVE DEL BÚFER después de un entero1
                // POLIMORFISMO: la variable es del tipo padre (Producto)
                // y guarda un objeto de la subclase (ProductoPapeleria)
                Producto nuevo = new ProductoPapeleria(cod, nom, pre, cant);
                inventario.agregarProducto(nuevo);
                break;

            case 2:
                inventario.consultarInventario();
                break;

            case 3:
                System.out.println("\n--- BUSCAR PRODUCTO ---");
                System.out.print("Ingrese el código del artículo a buscar: ");
                String codBuscar = lector.nextLine();
                Producto encontrado = inventario.buscarProducto(codBuscar);
                if (encontrado != null) {
                    System.out.println("Encontrado: " + encontrado.toString());
                } else {
                    System.out.println("Alerta: Producto no hallado con el código " + codBuscar);
                }
                break;

            case 4:
                System.out.println("\n--- MODIFICAR PRODUCTO ---");
                System.out.print("Ingrese el código del artículo a modificar: ");
                String codMod = lector.nextLine();
                Producto pMod = inventario.buscarProducto(codMod);
                if (pMod != null) {
                    System.out.println("Producto actual: " + pMod.toString());
                    System.out.print("Ingrese nuevo precio unitario ($): ");
                    double nuevoPre = lector.nextDouble();
                    lector.nextLine(); // LIMPIEZA CLAVE DEL BÚFER

                    System.out.print("Ingrese nuevo stock total: ");
                    int nuevoCant = lector.nextInt();
                    lector.nextLine(); // LIMPIEZA CLAVE DEL BÚFER

                    inventario.modificarProducto(codMod, nuevoPre, nuevoCant);
                } else {
                    System.out.println("No se encontró ningún producto con el código " + codMod);
                }
                break;

            case 5:
                System.out.println("\n--- ELIMINAR PRODUCTO ---");
                System.out.print("Ingrese el código del artículo a eliminar: ");
                String codElim = lector.nextLine();
                inventario.eliminarProducto(codElim);
                break;

            case 6:
                System.out.println("\nSaliendo del sistema de control. ¡Feliz día!");
                break;

            default:
                System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    // Método de ejecución principal (Entry Point)
    public static void main(String[] args) {
        Papeleria sistema = new Papeleria();
        sistema.iniciar();
    }
}
