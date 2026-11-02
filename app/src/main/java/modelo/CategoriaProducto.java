package modelo;

public enum CategoriaProducto {
    
    CAFE("Café"),
    ENSALADA("Ensalada"),
    PIZZA("Pizza");

    private final String nombre;
    CategoriaProducto(String nombre) { this.nombre = nombre; }
    public String getNombre() { return nombre; }
}
