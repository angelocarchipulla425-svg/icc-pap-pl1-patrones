package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Pollo extends IngredienteAdicional{
    public Pollo(Pedido pedidoEnvuelto){
        super(pedidoEnvuelto, "Pollo", new BigDecimal("1.50"));
    }
}
