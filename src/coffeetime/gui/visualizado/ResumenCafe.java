package coffeetime.gui.visualizado;

import coffeetime.base.Cafe;
import coffeetime.util.Util;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ResumenCafe extends JDialog {
    private JPanel contentPane;
    private JButton btnVerLote;
    private JButton btnVerImagen;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel lblArabico;
    private JLabel lblRobusta;

    public ResumenCafe(Cafe cafe) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

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
