package papeleria;

import java.util.ArrayList;

public class Inventario {
    // Lista dinámica privada para almacenar los productos del inventario
    // (es de tipo Producto, el padre, y guarda objetos de sus subclases)
    private ArrayList<Producto> productos;

    // Constructor que inicializa la colección en memoria
    public Inventario() {
        this.productos = new ArrayList<>();
    }

    // C: Registrar un nuevo producto (CREATE)
    public void agregarProducto(Producto p) {
        // Validación: evitar registrar códigos duplicados
        for (Producto prod : productos) {
            if (prod.getCodigo().equalsIgnoreCase(p.getCodigo())) {
                System.out.println("Error: Ya existe un producto registrado con el código " + p.getCodigo());
                return;
            }
        }
        productos.add(p);
        System.out.println("Producto agregado con éxito al inventario.");
    }

    // R: Consultar todos los productos en el inventario (READ)
    public void consultarInventario() {
        if (productos.isEmpty()) {
            System.out.println("Aviso: El inventario actual se encuentra vacío.");
            return;
        }
        System.out.println("\n========= LISTADO DE INVENTARIO =========");
        for (Producto p : productos) {
            // POLIMORFISMO: se ejecuta el toString() de la clase real del objeto
            System.out.println(p.toString());
        }
        System.out.println("=========================================");
    }

    // R2: Buscar un producto por su código
    public Producto buscarProducto(String cod) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(cod)) {
                return p;
            }
        }
        return null; // Retorna null si no encuentra coincidencias
    }

    // U: Modificar precio o stock de un producto (UPDATE)
    public void modificarProducto(String cod, double nuevoPrecio, int nuevaCantidad) {
        Producto p = buscarProducto(cod);
        if (p != null) {
            p.setPrecio(nuevoPrecio);     // El Setter valida que no sea negativo
            p.setCantidad(nuevaCantidad); // El Setter valida que no sea negativo
            System.out.println("Producto modificado exitosamente.");
        } else {
            System.out.println("Error: No se encontró ningún producto con el código " + cod);
        }
    }

    // D: Eliminar producto por completo (DELETE)
    public void eliminarProducto(String cod) {
        Producto p = buscarProducto(cod);
        if (p != null) {
            productos.remove(p);
            System.out.println("Producto eliminado correctamente del inventario.");
        } else {
            System.out.println("Error: No se encontró ningún producto con el código " + cod);
        }
    }
}
