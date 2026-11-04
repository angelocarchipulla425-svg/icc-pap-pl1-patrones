package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Pepperoni extends IngredienteAdicional{
    public Pepperoni(Pedido pedidoEnvuelto){
        super(pedidoEnvuelto, "Pepperoni", new BigDecimal("1.10"));
    }
}
