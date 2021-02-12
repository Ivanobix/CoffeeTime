package coffeetime.gui.visualizado;

import coffeetime.base.Lote;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

/**
 * Resumen Lote. Ventana dedicada a la visualización de todas las propiedades presentes en un elemento de tipo Lote.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ResumenLote extends JDialog {
    private final ResourceBundle idioma;

    private JPanel contentPane;
    private JButton btnVerFabricante;
    private JLabel lblIdentificador;
    private JLabel lblUnidades;
    private JLabel lblCoste;
    private JLabel lblEnvasado;
    private JLabel lblCaducidad;

    /**
     * Constructor.
     *
     * @param lote Lote a visualizar.
     */
    public ResumenLote(Lote lote) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        idioma = Util.obtenerTraducciones();
        setTitle(idioma.getString("ver.lote"));
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());

        contentPane.getRootPane().setDefaultButton(btnVerFabricante);

        lblIdentificador.setText(lote.getIdentificador());
        lblUnidades.setText(String.valueOf(lote.getNumeroUnidades()));
        lblCoste.setText(lote.getCosteTotal() + "€");
        lblEnvasado.setText(lote.getFechaDeEnvasado().toString());
        lblCaducidad.setText(lote.getFechaDeCaducidad().toString());

        btnVerFabricante.addActionListener(e -> new ResumenFabricante(lote.getFabricante()));
        btnVerFabricante.setMnemonic(KeyEvent.VK_1);
    }
}
