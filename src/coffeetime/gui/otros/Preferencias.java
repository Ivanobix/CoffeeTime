package coffeetime.gui.otros;

import javax.swing.*;

public class Preferencias extends JDialog {
    JSpinner spinFuente;
    JRadioButton rbTemaClaro;
    JRadioButton rbTemaOscuro;
    JComboBox<String> cbIdioma;
    JRadioButton rbAutoguardadoSi;
    JRadioButton rbAutoguardadoNo;
    JButton btnCancelar;
    JButton btnAceptar;
    SpinnerNumberModel snm;
    private JPanel contentPane;


    public Preferencias() {
        setContentPane(contentPane);
        initComponents();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initComponents() {
        snm = new SpinnerNumberModel();
        snm.setMaximum(25);
        snm.setMinimum(8);
        snm.setValue(12);
        spinFuente.setModel(snm);

        cbIdioma.addItem("ES");
        cbIdioma.addItem("EN");
        cbIdioma.addItem("FR");

        contentPane.getRootPane().setDefaultButton(btnAceptar);
        btnAceptar.requestFocus();

    }


}
