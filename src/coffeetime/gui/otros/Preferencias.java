package coffeetime.gui.otros;

import javax.swing.*;

/**
 * Preferencias. Ventana dedicada a la configuración de preferencias del usuario respecto
 * al funcionamiento o apariencia de la aplicación.
 *
 * @author Iván García Prieto
 * @version 23.01.2021
 */
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

    /**
     * Constructor.
     */
    public Preferencias() {
        setContentPane(contentPane);
        initComponents();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /**
     * Inicializar Componentes. Inicializa todos aquellos componentes visuales de
     * los que dispone esta clase y establece sus propiedades.
     */
    private void initComponents() {
        setIconImage(new ImageIcon(this.getClass().getResource("/general/logo.png")).getImage());
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
