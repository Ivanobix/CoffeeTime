package coffeetime.gui.principal;

import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControladorMenuPrincipal implements ActionListener {

    private static final String EXTENSION_FICHEROS = ".dat";
    private static final String NOMBRE_EXTENSION_FICHEROS = "dat";
    private final MenuPrincipal menuPrincipal;
    private final Modelo modelo;

    public ControladorMenuPrincipal(MenuPrincipal menuPrincipal, Modelo modelo) {
        this.menuPrincipal = menuPrincipal;
        this.modelo = modelo;
        initHandlers();
    }

    private void initHandlers() {
        menuPrincipal.btnCafes.addActionListener(this);
        menuPrincipal.btnConfiguracion.addActionListener(this);
        menuPrincipal.btnFabricantes.addActionListener(this);
        menuPrincipal.btnLotes.addActionListener(this);
        menuPrincipal.mnitGuardar.addActionListener(this);
        menuPrincipal.mnitCargar.addActionListener(this);

    }

    private void guardarDatos() {
        JFileChooser selector = new JFileChooser();
        selector.setAcceptAllFileFilterUsed(false);
        selector.setFileFilter(new FileNameExtensionFilter(EXTENSION_FICHEROS, NOMBRE_EXTENSION_FICHEROS));
        int seleccion = selector.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = new File(selector.getSelectedFile().getAbsolutePath() + EXTENSION_FICHEROS);
            //File fichero = selector.getSelectedFile();
            try {
                modelo.guardarDatos(fichero);
            } catch (IOException e1) {
                Util.mostrarError("Error al guardar el fichero");
            }
        }
    }

    private void cargarDatos() {
        JFileChooser selectorCarga = new JFileChooser();
        selectorCarga.setAcceptAllFileFilterUsed(false);
        selectorCarga.setFileFilter(new FileNameExtensionFilter(EXTENSION_FICHEROS, NOMBRE_EXTENSION_FICHEROS));
        int seleccionCarga = selectorCarga.showOpenDialog(null);
        if (seleccionCarga == JFileChooser.APPROVE_OPTION) {
            File ficheroCarga = selectorCarga.getSelectedFile();
            try {
                modelo.cargarDatos(ficheroCarga);
            } catch (ClassNotFoundException | IOException e1) {
                Util.mostrarError("Error al abrir el fichero");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnCafes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_CAFES), modelo);
                break;
            case "btnFabricantes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_FABRICANTES), modelo);
                break;
            case "btnLotes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_LOTES), modelo);
                break;

            case "btnConfiguracion":

                break;
            case "mnitGuardar":
                guardarDatos();
                break;
            case "mnitCargar":
                cargarDatos();
                break;
            case "mnitNuevo":

                break;
            case "mnitDeshacer":

                break;
        }
    }
}
