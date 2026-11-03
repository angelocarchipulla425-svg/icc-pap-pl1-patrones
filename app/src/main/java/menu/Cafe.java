package menu;

import java.math.BigDecimal;

public class Cafe extends PlatilloBase{

    public Cafe() {
        super("PROT-CAFE", "Café", new BigDecimal("2.20"), "Café");
    }


    public Cafe(Cafe fuente, String nuevoId){
        super(fuente, nuevoId);
    }

    @Override
    public PlatilloBase copiar(String nuevoId) {
        return new Cafe(this, nuevoId);
    }
    
}
