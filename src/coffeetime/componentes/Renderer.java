package coffeetime.componentes;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;

import javax.swing.*;
import java.awt.*;

public class Renderer implements ListCellRenderer {

    public static final int CAFES = Color.CYAN.getRGB();
    public static final int LOTES = Color.GREEN.getRGB();
    public static final int FABRICANTES = Color.RED.getRGB();

    private JLabel lblIdentificador;
    private JLabel lblTituloUno;
    private JLabel lblTituloDos;
    private JLabel lblInfoUno;
    private JLabel lblInfoDos;
    private JPanel renderer;
    private JSeparator separadorInferior;
    private JSeparator separadorSuperior;
    private JLabel lblImagen;

    public Renderer(int tipo) {
        Color color = new Color(tipo);
        separadorSuperior.setForeground(color);
        separadorInferior.setForeground(color);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Cafe) {
            rellenarDatosCafe((Cafe) value);
        } else if (value instanceof Lote) {
            rellenarDatosLote((Lote) value);
        } else {
            rellenarDatosFabricante((Fabricante) value);
        }
        return renderer;
    }

    private void rellenarDatosCafe(Cafe cafe) {
        lblIdentificador.setText(cafe.getIdentificador());

        lblTituloUno.setText("Nombre: ");
        lblInfoUno.setText(cafe.getNombre());

        lblTituloDos.setText("Composición: ");
        lblInfoDos.setText("A: " + cafe.getPorcentajeArabico() + " R: " + cafe.getPorcentajeRobusta());

        lblImagen.setIcon(new ImageIcon(cafe.getImagenPromocional()));
    }

    private void rellenarDatosLote(Lote lote) {
        lblIdentificador.setText(lote.getIdentificador());

        lblTituloUno.setText("Unidades: ");
        lblInfoUno.setText(String.valueOf(lote.getNumeroUnidades()));

        lblTituloDos.setText("Coste: ");
        lblInfoDos.setText(String.valueOf(lote.getCosteTotal()));

        lblImagen.setVisible(false);
    }

    private void rellenarDatosFabricante(Fabricante fabricante) {
        lblIdentificador.setText(fabricante.getIdentificador());

        lblTituloUno.setText("Nombre: ");
        lblInfoUno.setText(fabricante.getNombre());

        lblTituloDos.setText("Dirección: ");
        lblInfoDos.setText(fabricante.getDireccion());

        lblImagen.setVisible(false);
    }
}
