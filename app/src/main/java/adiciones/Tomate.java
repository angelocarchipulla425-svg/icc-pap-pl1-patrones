package adiciones;

import modelo.Pedido;
import java.math.BigDecimal;

public class Tomate extends IngredienteAdicional{
    public Tomate(Pedido pedidoEnvuelto){
        super(pedidoEnvuelto, "Tomate", new BigDecimal("1.10"));

    }
}
