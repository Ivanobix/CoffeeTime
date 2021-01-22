package coffeetime.gui.otros;

import coffeetime.util.Util;

import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControladorPreferencias {

    private final Preferencias preferencias;
    private final ResourceBundle idioma;

    public ControladorPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
        idioma = Util.obtenerTraducciones();
        marcarPreferenciasAnteriores();
        crearAtajos();
        initHandlers();
    }

    private void initHandlers() {
        preferencias.btnAceptar.addActionListener(e -> {
            guardarPreferencias();
            preferencias.dispose();
        });

        preferencias.btnCancelar.addActionListener(e -> preferencias.dispose());
    }

    private void crearAtajos() {
        preferencias.btnAceptar.setMnemonic(KeyEvent.VK_1);
        preferencias.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    private void guardarPreferencias() {
        Properties propiedades = new Properties();

        propiedades.put("TamanoFuente", preferencias.spinFuente.getValue().toString());
        propiedades.put("Idioma", preferencias.cbIdioma.getSelectedItem());

        String tema;
        if (preferencias.rbTemaClaro.isSelected()) tema = "claro";
        else tema = "oscuro";
        propiedades.put("Tema", tema);

        String autoGuardado;
        if (preferencias.rbAutoguardadoSi.isSelected()) autoGuardado = "si";
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
            preferencias.spinFuente.setValue(tamanoFuente);

            String idioma = properties.getProperty("Idioma");
            preferencias.cbIdioma.setSelectedItem(idioma);

            if (properties.getProperty("Tema").equals("claro")) {
                preferencias.rbTemaClaro.setSelected(true);
            }

            if (properties.getProperty("Autoguardado").equals("si")) {
                preferencias.rbAutoguardadoSi.setSelected(true);
            }

        } catch (IOException e) {
            guardarPreferencias();
        }
    }
}
