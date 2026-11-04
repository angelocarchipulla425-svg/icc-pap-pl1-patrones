package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public abstract class IngredienteAdicional implements Pedido{
    protected Pedido pedidoEnvuelto;
    protected String nombreIngrediente;
    protected BigDecimal costoIngrediente;

    public IngredienteAdicional(Pedido pedidoEnvuelto, String nombreIngrediente, BigDecimal costoIngrediente){
        if (pedidoEnvuelto == null){
            throw new IllegalArgumentException("No se puede decorar un pedido vacío.");
        }
        this.pedidoEnvuelto = pedidoEnvuelto;
        this.nombreIngrediente = nombreIngrediente;
        this.costoIngrediente = costoIngrediente;
    }

    @Override 
    public String id(){
        return pedidoEnvuelto.id();
    }

    @Override 
    public String descripcion(){
        return pedidoEnvuelto.descripcion() + " + " + nombreIngrediente;
    }

    @Override 
    public BigDecimal precio(){
        return pedidoEnvuelto.precio().add(costoIngrediente);
    }

    @Override 
    public String tipo(){
        return pedidoEnvuelto.tipo();
    }
}
