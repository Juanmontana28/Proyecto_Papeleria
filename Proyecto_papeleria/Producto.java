package papeleria;

// Clase padre ABSTRACTA: ya no se crean objetos Producto directamente
public abstract class Producto {
    // Atributos protegidos (Encapsulamiento)
    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int cantidad;

    // Constructor con parámetros que utiliza la palabra clave 'this'
    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        // Se utilizan los setters internos para aplicar las reglas de validación desde la creación
        setPrecio(precio);
        setCantidad(cantidad);
    }

    // Métodos de Acceso (Getters y Setters)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    // Setter de precio con validación de regla de negocio
    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        } else {
            System.out.println("Error: El precio no puede ser negativo. Se asignará $0.0 por defecto.");
            this.precio = 0.0;
        }
    }

    public int getCantidad() {
        return this.cantidad;
    }

    // Setter de cantidad con validación de regla de negocio
    public void setCantidad(int cantidad) {
        if (cantidad >= 0) {
            this.cantidad = cantidad;
        } else {
            System.out.println("Error: El stock no puede ser negativo. Se asignará 0 por defecto.");
            this.cantidad = 0;
        }
    }

    // MÉTODO ABSTRACTO: no tiene cuerpo, las subclases están obligadas a implementarlo
    @Override
    public abstract String toString();
}

// Subclase concreta de Producto: HEREDA todos los atributos y métodos del padre
class ProductoPapeleria extends Producto {

    // El constructor llama al constructor del padre con super(...)
    public ProductoPapeleria(String codigo, String nombre, double precio, int cantidad) {
        super(codigo, nombre, precio, cantidad);
    }

    // SOBRESCRITURA: implementa el método abstracto del padre
    @Override
    public String toString() {
        return "CÓDIGO: " + getCodigo() + " | NOMBRE: " + getNombre() + " | PRECIO: $" + getPrecio() + " | STOCK: " + getCantidad() + " unidades";
    }
}
