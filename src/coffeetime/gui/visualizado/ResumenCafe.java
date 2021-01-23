package coffeetime.gui.visualizado;

import coffeetime.base.Cafe;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Resumen Café. Ventana dedicada a la visualización de todas las propiedades presentes en un elemento de tipo Café.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ResumenCafe extends JDialog {
    private JPanel contentPane;
    private JButton btnVerLote;
    private JButton btnVerImagen;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel lblArabico;
    private JLabel lblRobusta;

    /**
     * Constructor.
     *
     * @param cafe Café a visualizar.
     */
    public ResumenCafe(Cafe cafe) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());

        contentPane.getRootPane().setDefaultButton(btnVerImagen);
        btnVerImagen.requestFocus();

        lblIdentificador.setText(cafe.getIdentificador());
        lblNombre.setText(cafe.getNombre());
        lblArabico.setText(cafe.getPorcentajeArabico() + "%");
        lblRobusta.setText(cafe.getPorcentajeRobusta() + "%");

        btnVerLote.addActionListener(e -> new ResumenLote(cafe.getLote()));
        btnVerLote.setMnemonic(KeyEvent.VK_1);

        btnVerImagen.addActionListener(e -> Util.mostrarImagen(cafe.getImagenPromocional()));
        btnVerImagen.setMnemonic(KeyEvent.VK_2);

    }
}
