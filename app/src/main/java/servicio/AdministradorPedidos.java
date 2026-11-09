package servicio;

import modelo.Pedido;
import menu.RegistroPlatillos;
import adiciones.*;


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

    public Pedido crearPedido(String tipoPlatillo){
        String nuevoId = String.format("P-%03d", contadorId++);
        Pedido nuevoPedido = registro.clonar(tipoPlatillo, nuevoId);
        listaPedidos.add(nuevoPedido);
        return nuevoPedido;
    }

    public boolean esCompatible(String tipoProducto, String ingrediente) {
        tipoProducto = tipoProducto.toLowerCase();
        ingrediente = ingrediente.toLowerCase();

        if (tipoProducto.contains("café") || tipoProducto.contains("cafe")){
            return ingrediente.equals("azúcar") || ingrediente.equals("azucar") || ingrediente.equals("crema"); 
        } else if (tipoProducto.contains("ensalada")){
            return ingrediente.equals("tomate") || ingrediente.equals("pollo");
        } else if (tipoProducto.contains("pizza")) {
            return ingrediente.equals("queso") || ingrediente.equals("pepperoni");
        }
        return false;
    }

    public Pedido agregarIngrediente(Pedido pedidoActual, String nombreIngrediente){
        if (pedidoActual == null){
            throw new IllegalStateException("Debe seleccionar un pedido de la lista.");
        }
        if (!esCompatible(pedidoActual.tipo(), nombreIngrediente)){
            throw new IllegalArgumentException("Ingrediente incompatible (" + nombreIngrediente + ") " +
                "para el producto: " + pedidoActual.tipo()
            );
        }

        Pedido pedidoDecorado;
        String ingLower = nombreIngrediente.toLowerCase();

        if (ingLower.contains("azúcar") || ingLower.contains("azucar")) {
            pedidoDecorado = new Azucar(pedidoActual);
        } else if (ingLower.equals("crema")){
            pedidoDecorado = new Crema(pedidoActual);
        } else if (ingLower.equals("tomate")){
            pedidoDecorado = new Pollo(pedidoActual);
        } else if (ingLower.equals("pollo")){
            pedidoDecorado = new Tomate(pedidoActual);
        } else if (ingLower.equals("queso")){
            pedidoDecorado = new Queso(pedidoActual);
        } else if (ingLower.equals("pepperoni")){
            pedidoDecorado = new Pepperoni(pedidoActual);
        } else {
            throw new IllegalArgumentException("Ingrediente no reconocido.");
        }

        // Reemplazar en la lista manteniendo el mismo Id y referencia envuelta
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).id().equals(pedidoActual.id())) {
                listaPedidos.set(i, pedidoDecorado);
                break;
            }
        }
        
        return pedidoDecorado;
    }

    public List<Pedido> getListaPedidos(){
        return listaPedidos;
    }

    public void reiniciarPedidos() {
        listaPedidos.clear();
        contadorId = 1;
    }

    
}
