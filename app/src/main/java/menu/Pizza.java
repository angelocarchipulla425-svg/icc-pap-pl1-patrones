package menu;

import java.math.BigDecimal;

public class Pizza extends PlatilloBase{

    public Pizza(){
        super("PROT-PIZZA", "Pizza sencilla", new BigDecimal("5.00"), "Pizza");
    }

    public Pizza(Pizza fuente, String nuevoId){
        super(fuente, nuevoId);
    }

    @Override
    public PlatilloBase copiar(String nuevoId) {
        return new Pizza(this, nuevoId);
    }
    
}
