package modelo;

import java.math.BigDecimal;

public interface Pedido {
    String id();
    String descripcion();
    BigDecimal precio();
    String tipo();
} 
