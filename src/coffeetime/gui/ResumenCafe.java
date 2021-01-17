package coffeetime.gui;

import coffeetime.base.Cafe;
import coffeetime.util.Util;

import javax.swing.*;

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

        lblIdentificador.setText(cafe.getIdentificador());
        lblNombre.setText(cafe.getNombre());
        lblArabico.setText(cafe.getPorcentajeArabico() + "%");
        lblRobusta.setText(cafe.getPorcentajeRobusta() + "%");

        btnVerLote.addActionListener(e -> new ResumenLote(cafe.getLote()));

        btnVerImagen.addActionListener(e -> Util.mostrarImagen(cafe.getImagenPromocional()));


    }
}
