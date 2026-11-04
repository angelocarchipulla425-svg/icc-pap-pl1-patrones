package servicio;

import modelo.Pedido;
import menu.RegistroPlatillos;
import adiciones.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class AdministradorPedidos {
    private RegistroPlatillos registro;
    private List<Pedido> listaPedidos;
    private int contadorId = 1;

    public AdministradorPedidos(){
        this.registro = new RegistroPlatillos();
        this.listaPedidos = new ArrayList<>();
        inicializarRegistro();
    }

    private void inicializarRegistro(){
        registro.registrar("Café", new menu.Cafe());
        registro.registrar("Ensalada", new menu.Ensalada());
        registro.registrar("Pizza", new menu.Pizza());
    }
}
