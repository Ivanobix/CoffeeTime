package coffeetime.componentes;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class Renderer implements ListCellRenderer {

    public static final int CAFES = Color.CYAN.getRGB();
    public static final int LOTES = Color.GREEN.getRGB();
    public static final int FABRICANTES = Color.RED.getRGB();

    private final ResourceBundle idioma;

    private JLabel lblIdentificador;
    private JLabel lblTituloUno;
    private JLabel lblTituloDos;
    private JLabel lblInfoUno;
    private JLabel lblInfoDos;
    private JPanel renderer;
    private JSeparator separadorInferior;
    private JSeparator separadorSuperior;
    private JPanel pnOeste;
    private JPanel pnEste;
    private JPanel pnNorte;
    private JPanel pnCentral;

    public Renderer(int tipo) {
        idioma = Util.obtenerTraducciones();
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

        if (isSelected) {
            resaltar();

        } else {
            restablecerFondo();
        }

        return renderer;
    }

    private void rellenarDatosCafe(Cafe cafe) {
        lblIdentificador.setText(cafe.getIdentificador());

        lblTituloUno.setText("");
        lblTituloUno.setVisible(false);
        lblInfoUno.setText("");
        lblInfoUno.setIcon(Util.escalarImagen(new ImageIcon(cafe.getImagenPromocional()), 60, 60));

        lblTituloDos.setText(idioma.getString("renderer.nombre"));
        lblInfoDos.setText(cafe.getNombre());

    }

    private void rellenarDatosLote(Lote lote) {
        lblIdentificador.setText(lote.getIdentificador());

        lblTituloUno.setText(idioma.getString("renderer.unidades"));
        lblInfoUno.setText(String.valueOf(lote.getNumeroUnidades()));

        lblTituloDos.setText(idioma.getString("renderer.coste"));
        lblInfoDos.setText(String.valueOf(lote.getCosteTotal()));

    }

    private void rellenarDatosFabricante(Fabricante fabricante) {
        lblIdentificador.setText(fabricante.getIdentificador());

        lblTituloUno.setText(idioma.getString("renderer.nombre"));
        lblInfoUno.setText(fabricante.getNombre());

        lblTituloDos.setText(idioma.getString("renderer.direccion"));
        lblInfoDos.setText(fabricante.getDireccion());

    }

    private void resaltar() {
        Color color = new Color(28, 28, 28);
        pnEste.setBackground(color);
        pnOeste.setBackground(color);
        pnNorte.setBackground(color);
        pnCentral.setBackground(color);
    }

    private void restablecerFondo() {
        pnEste.setBackground(Color.DARK_GRAY);
        pnOeste.setBackground(Color.DARK_GRAY);
        pnNorte.setBackground(Color.DARK_GRAY);
        pnCentral.setBackground(Color.DARK_GRAY);
    }
}
