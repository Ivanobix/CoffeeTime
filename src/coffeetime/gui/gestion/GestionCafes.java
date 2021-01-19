package coffeetime.gui.gestion;

import coffeetime.base.Lote;

import javax.swing.*;

public class GestionCafes extends JDialog {
    JPanel contentPane;
    JTextField txtNombre;
    JTextField txtArabico;
    JTextField txtRobusta;
    JTextField txtRutaImagen;
    JComboBox<Lote> cbLote;
    JButton btnGestionar;
    JButton btnCancelar;
    JButton btnSeleccionarImagen;
    JLabel imgPromocional;
    DefaultComboBoxModel<Lote> dcbm;


    public GestionCafes() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnGestionar);
        btnGestionar.requestFocus();

        dcbm = new DefaultComboBoxModel<>();
        cbLote.setModel(dcbm);
    }

}
