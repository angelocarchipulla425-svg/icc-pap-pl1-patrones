import javax.swing.SwingUtilities;
import vista.PantallaPedidos;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaPedidos().setVisible(true);
        });
    }
}
