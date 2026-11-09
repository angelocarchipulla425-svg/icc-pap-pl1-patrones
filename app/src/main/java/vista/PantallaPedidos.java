package vista;

import modelo.Pedido;
import servicio.AdministradorPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PantallaPedidos extends JFrame {
    private AdministradorPedidos admin;
    private JComboBox<String> comboProductos;
    private JComboBox<String> comboIngredientes;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    
    // Componentes del panel de detalle (inferior)
    private JLabel lblDetalleId, lblDetalleProducto, lblDetalleIngredientes, lblDetalleTotal;
    private Pedido pedidoSeleccionado;

    public PantallaPedidos() {
        this.admin = new AdministradorPedidos();
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("PedidoLab");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Usamos un BorderLayout general limpio
        setLayout(new BorderLayout(10, 10));
    }

    private void inicializarComponentes() {
        // Panel superior, controles (productos e ingredientes)

        JPanel panelNorte = new JPanel();
        panelNorte.setLayout(new BoxLayout(panelNorte, BoxLayout.Y_AXIS));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        // Subpanel de producto base (Prototype)
        JPanel panelProducto = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelProducto.setBorder(BorderFactory.createTitledBorder("Selección de Producto"));
        comboProductos = new JComboBox<>(new String[]{"Café", "Ensalada", "Pizza"});
        JButton btnClonar = new JButton("Elegir");
        panelProducto.add(new JLabel("Producto:"));
        panelProducto.add(comboProductos);
        panelProducto.add(btnClonar);

        // Subpanel de ingredientes (Decorator)
        JPanel panelIngrediente = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelIngrediente.setBorder(BorderFactory.createTitledBorder("Agregar Ingredientes"));
        comboIngredientes = new JComboBox<>(new String[]{"Azúcar", "Crema", "Tomate", "Pollo", "Queso", "Pepperoni"});
        JButton btnAgregar = new JButton("Agregar Ingrediente");
        JButton btnReiniciar = new JButton("Reiniciar");
        panelIngrediente.add(new JLabel("Ingrediente:"));
        panelIngrediente.add(comboIngredientes);
        panelIngrediente.add(btnAgregar);
        panelIngrediente.add(btnReiniciar);

        panelNorte.add(panelProducto);
        panelNorte.add(panelIngrediente);
        add(panelNorte, BorderLayout.NORTH);

        // Panel central, tabla de pedidos creados

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Tipo", "Descripción / Composición", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaPedidos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Pedidos Creados (Tabla General)"));
        scrollTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                // Convierte el punto del scroll al sistema de coordenadas de la tabla
                int filaClicked = tablaPedidos.rowAtPoint(SwingUtilities.convertPoint(scrollTabla, e.getPoint(), tablaPedidos));
                if (filaClicked == -1) { // Si se hizo clic en el espacio vacío/gris
                    tablaPedidos.clearSelection();
                    pedidoSeleccionado = null;
                    limpiarDetalle();
                }
            }
        });
        // Panel contenedor para dar margen al centro
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelCentro.add(scrollTabla, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        // panel interior, detalle del pedido seleccionado

        JPanel panelDetalle = new JPanel(new GridLayout(4, 1, 3, 3));
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle del Pedido Seleccionado"));
        ((javax.swing.border.TitledBorder) panelDetalle.getBorder()).setTitleFont(new Font("Arial", Font.BOLD, 12));

        lblDetalleId = new JLabel("ID de pedido: ");
        lblDetalleProducto = new JLabel("Producto: ");
        lblDetalleIngredientes = new JLabel("Ingredientes: ");
        lblDetalleTotal = new JLabel("Total del pedido: $0.00");
        lblDetalleTotal.setFont(new Font("Arial", Font.BOLD, 13));

        panelDetalle.add(lblDetalleId);
        panelDetalle.add(lblDetalleProducto);
        panelDetalle.add(lblDetalleIngredientes);
        panelDetalle.add(lblDetalleTotal);

        // Contenedor sur con margen
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        panelSur.add(panelDetalle, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);


        // eventos de los botones y componentes

        btnClonar.addActionListener(e -> {
            try {
                String tipo = (String) comboProductos.getSelectedItem();
                Pedido nuevo = admin.crearPedido(tipo);
                actualizarTabla();
                seleccionarUltimoPedido();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAgregar.addActionListener(e -> {
            try {
                if (pedidoSeleccionado == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione un pedido de la tabla primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String ingrediente = (String) comboIngredientes.getSelectedItem();
                pedidoSeleccionado = admin.agregarIngrediente(pedidoSeleccionado, ingrediente);
                actualizarTabla();
                actualizarDetalle();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación Rechazada", JOptionPane.ERROR_MESSAGE);
            }
        });

        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            int fila = tablaPedidos.getSelectedRow();
            if (fila >= 0 && fila < modeloTabla.getRowCount()) {
                String idBuscado = (String) modeloTabla.getValueAt(fila, 0);
                for (Pedido p : admin.getListaPedidos()) {
                    if (p.id().equals(idBuscado)) {
                        pedidoSeleccionado = p;
                        actualizarDetalle();
                        break;
                    }
                }
            }
        });

        btnReiniciar.addActionListener(e -> {
            admin.reiniciarPedidos();
            pedidoSeleccionado = null;
            actualizarTabla();
            limpiarDetalle();
        });
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Pedido p : admin.getListaPedidos()) {
            modeloTabla.addRow(new Object[]{
                p.id(),
                p.tipo(),
                p.descripcion(),
                String.format("$%.2f", p.precio())
            });
        }
    }

    private void actualizarDetalle() {
        if (pedidoSeleccionado != null) {
            lblDetalleId.setText("ID de pedido: " + pedidoSeleccionado.id());
            lblDetalleProducto.setText("Producto: " + pedidoSeleccionado.tipo());
            lblDetalleIngredientes.setText("Ingredientes: " + pedidoSongDescription(pedidoSeleccionado));
            lblDetalleTotal.setText(String.format("Total del pedido: $%.2f", pedidoSeleccionado.precio()));
        }
    }

    // Método auxiliar para limpiar la descripción en el detalle si se desea
    private String pedidoSongDescription(Pedido p) {
        return p.descripcion();
    }

    private void limpiarDetalle() {
        lblDetalleId.setText("ID de pedido: ");
        lblDetalleProducto.setText("Producto: ");
        lblDetalleIngredientes.setText("Ingredientes: ");
        lblDetalleTotal.setText("Total del pedido: $0.00");
    }

    private void seleccionarUltimoPedido() {
        if (tablaPedidos.getRowCount() > 0) {
            int ultimaFila = tablaPedidos.getRowCount() - 1;
            tablaPedidos.setRowSelectionInterval(ultimaFila, ultimaFila);
        }
    }
}