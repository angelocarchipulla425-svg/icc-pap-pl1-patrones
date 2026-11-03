package menu;

import java.math.BigDecimal;

public class Ensalada extends PlatilloBase{
    
    public Ensalada(){
        super("PROT-ENSALADA", "Ensalada sencilla", new BigDecimal("3.00"), "Ensalada");
    }

    public Ensalada(Ensalada fuente, String nuevoId) {
        super(fuente, nuevoId);
    }

    @Override
    public PlatilloBase copiar(String nuevoId) {
        return new Ensalada(this, nuevoId);
    }
}
