package coffeetime.gui.visualizado;

import coffeetime.base.Fabricante;

import javax.swing.*;

public class ResumenFabricante extends JDialog {
    private JPanel contentPane;
    private JLabel lblIdentificador;
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblTrabajadores;
    private JLabel lblInternacional;
    private JLabel lblFechaAlta;

    public ResumenFabricante(Fabricante fabricante) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        lblIdentificador.setText(fabricante.getIdentificador());
        lblNombre.setText(fabricante.getNombre());
        lblDireccion.setText(fabricante.getDireccion());
        lblTrabajadores.setText(String.valueOf(fabricante.getTrabajadores()));
        lblFechaAlta.setText(fabricante.getFechaAlta().toString());

        if (fabricante.isInternacional()) {
            lblInternacional.setText("SI");
        } else {
            lblInternacional.setText("NO");
        }

    }
}
