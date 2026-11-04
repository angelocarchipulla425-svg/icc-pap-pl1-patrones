package menu;


import modelo.Pedido;
import java.math.BigDecimal;


public abstract class PlatilloBase implements Pedido, PlatilloClonable<PlatilloBase> {
    protected String id;
    protected String nombre;
    protected BigDecimal precioBase;
    protected String tipoProducto;

    public PlatilloBase(String id, String nombre, BigDecimal precioBase, String tipoProducto) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.tipoProducto = tipoProducto;
    }

    // Copia

    public PlatilloBase(PlatilloBase fuente, String nuevoId) {
        this.id = nuevoId;
        this.nombre = fuente.nombre;
        this.precioBase = fuente.precioBase;
        this.tipoProducto = fuente.tipoProducto;
    }

    @Override 
    public String id() { return id; }

    @Override 
    public String descripcion() { return nombre; }

    @Override 
    public BigDecimal precio() { return precioBase; }

    @Override 
    public String tipo() { return tipoProducto; }

    @Override 
    public abstract PlatilloBase copiar(String nuevoId);
}
