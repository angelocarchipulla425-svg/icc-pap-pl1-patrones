package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Azucar extends IngredienteAdicional{
    public Azucar(Pedido pedidoEnvuelto){ 
        super(pedidoEnvuelto,
             "Azúcar",
              new BigDecimal("0.50"));}
}
