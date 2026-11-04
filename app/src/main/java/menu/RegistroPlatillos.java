package menu;

import java.util.HashMap;
import java.util.Map;

public class RegistroPlatillos {
    private Map <String, PlatilloBase> prototipos = new HashMap<>();
    
    public void registrar(String clave, PlatilloBase prototipo){
        prototipos.put(clave.toLowerCase(), prototipo);
    }

    public PlatilloBase clonar(String clave, String nuevoId){
        PlatilloBase proto = prototipos.get(clave.toLowerCase());
        if(proto == null){
            throw new IllegalArgumentException("Platillo inexistente: " + clave);
        }
        return proto.copiar(nuevoId);
    }
}
