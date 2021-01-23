package coffeetime.gui.visualizado;

import coffeetime.base.Lote;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Resumen Lote. Ventana dedicada a la visualización de todas las propiedades presentes en un elemento de tipo Lote.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
public class ResumenLote extends JDialog {
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
