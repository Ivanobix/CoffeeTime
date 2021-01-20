package coffeetime.gui.otros;

import coffeetime.util.Util;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class Preferencias extends JDialog {
    private JPanel contentPane;
    private JSpinner spinFuente;
    private JRadioButton rbTemaClaro;
    private JRadioButton rbTemaOscuro;
    private JComboBox<String> cbIdioma;
    private JRadioButton rbAutoguardadoSi;
    private JRadioButton rbAutoguardadoNo;
    private JButton btnCancelar;
    private JButton btnAceptar;
    private SpinnerNumberModel snm;
    private ResourceBundle idioma;

    public Preferencias() {
        idioma = Util.obtenerTraducciones();
        setContentPane(contentPane);
        initComponents();
        setLocationRelativeTo(null);
        pack();
        initHandlers();
        setVisible(true);
        marcarPreferenciasAnteriores();
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

    private void initHandlers() {
        btnAceptar.addActionListener(e -> {
            guardarPreferencias();
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    private void guardarPreferencias() {
        Properties propiedades = new Properties();

        propiedades.put("TamanoFuente", spinFuente.getValue().toString());
        propiedades.put("Idioma", cbIdioma.getSelectedItem());

        String tema;
        if (rbTemaClaro.isSelected()) tema = "claro";
        else tema = "oscuro";
        propiedades.put("Tema", tema);

        String autoGuardado;
        if (rbAutoguardadoSi.isSelected()) autoGuardado = "si";
        else autoGuardado = "no";
        propiedades.put("Autoguardado", autoGuardado);

        try {
            propiedades.store(new FileWriter("data/preferencias.conf"), "Coffe Time");
            Util.mostrarAviso(idioma.getString("aviso.reiniciarAplicacion"));

        } catch (IOException e) {
            Util.mostrarError(idioma.getString("error.guardarPreferencias"));
        }
    }

    private void marcarPreferenciasAnteriores() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/preferencias.conf"));

            int tamanoFuente = Integer.parseInt(properties.getProperty("TamanoFuente"));
            spinFuente.setValue(tamanoFuente);

            String idioma = properties.getProperty("Idioma");
            cbIdioma.setSelectedItem(idioma);

            if (properties.getProperty("Tema").equals("claro")) {
                rbTemaClaro.setSelected(true);
            }

            if (properties.getProperty("Autoguardado").equals("si")) {
                rbAutoguardadoSi.setSelected(true);
            }

        } catch (IOException e) {
            guardarPreferencias();
        }
    }

}
