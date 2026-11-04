package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Queso extends IngredienteAdicional{
    public Queso(Pedido pedidoEnvuelto){
        super(pedidoEnvuelto, "Queso", new BigDecimal("1.00"));
    }
}
