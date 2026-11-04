package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Crema extends IngredienteAdicional{
    public Crema(Pedido pedidoEnvuelto) {
        super(pedidoEnvuelto,
             "Crema",
              new BigDecimal("1.20"));
    }
}
