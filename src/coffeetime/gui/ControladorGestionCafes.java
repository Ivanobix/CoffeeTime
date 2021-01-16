package coffeetime.gui;

import coffeetime.base.Cafe;
import coffeetime.base.Lote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Controlador Gestión Cafés. Controlador para la ventana de gestión de cafés
 * dedicado a la recogida de eventos, así como la comprobación de datos
 * introducidos e insercción de los datos en cada campo de texto (en el caso de
 * modificación).
 *
 * @author Iván García Prieto
 * @version 16.01.2021
 */
public class ControladorGestionCafes implements ActionListener {

    private final GestionCafes ventanaGestionCafe;
    private final Modelo modelo;
    private final boolean modificando;
    private final Cafe cafeAModificar;

    /**
     * Constructor para crear un nuevo café.
     *
     * @param modelo             Modelo de la aplicación.
     * @param ventanaGestionCafe Ventana de gestión de café a controlar.
     */
    public ControladorGestionCafes(GestionCafes ventanaGestionCafe, Modelo modelo) {
        this.ventanaGestionCafe = ventanaGestionCafe;
        this.modelo = modelo;
        modificando = false;
        cafeAModificar = null;
        cargarLotes();
        initHandlers();

        ventanaGestionCafe.btnGestionar.setText("Añadir");

    }

    /**
     * Constructor para modificar un café.
     *
     * @param modelo             Modelo de la aplicación.
     * @param ventanaGestionCafe Ventana de gestión de café a controlar.
     * @param cafe               Café a modificar.
     */
    public ControladorGestionCafes(GestionCafes ventanaGestionCafe, Modelo modelo, Cafe cafe) {
        this.ventanaGestionCafe = ventanaGestionCafe;
        this.modelo = modelo;
        modificando = true;
        cafeAModificar = cafe;
        cargarLotes();
        initHandlers();

        ventanaGestionCafe.txtNombre.setText(cafe.getNombre());
        ventanaGestionCafe.txtArabico.setText(String.valueOf(cafe.getPorcentajeArabico()));
        ventanaGestionCafe.txtRobusta.setText(String.valueOf(cafe.getPorcentajeRobusta()));
        ventanaGestionCafe.txtRutaImagen.setText(cafe.getImagenPromocional());

        ventanaGestionCafe.cbLote.setSelectedItem(cafe.getLote());

        ImageIcon iconoOriginal = new ImageIcon(ventanaGestionCafe.txtRutaImagen.getText());
        ventanaGestionCafe.imgPromocional.setIcon(Util.escalarImagen(iconoOriginal, 70, 70));

        ventanaGestionCafe.btnGestionar.setText("Modificar");

    }

    /**
     * Inicializar Manejadores. Inicializa todos los manejadores de eventos
     * necesarios para el correcto funcionamiento de la aplicación.
     */
    private void initHandlers() {
        ventanaGestionCafe.btnSeleccionarImagen.addActionListener(this);
        ventanaGestionCafe.btnGestionar.addActionListener(this);
        ventanaGestionCafe.btnCancelar.addActionListener(this);
    }

    private void cargarLotes() {
        ArrayList<Lote> lotes = modelo.getLotes();
        for (Lote lote : lotes) {
            ventanaGestionCafe.dcbm.addElement(lote);
        }
    }

    private void selecionarImagen() {
        JFileChooser selector = new JFileChooser();
        selector.setFileFilter(new FileNameExtensionFilter("JPG & PNG", "jpg", "png"));
        int opcion = selector.showOpenDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File fichero = selector.getSelectedFile();
            String ruta = fichero.getAbsolutePath();
            ImageIcon iconoAMostrar = Util.escalarImagen(new ImageIcon(ruta), 70, 70);
            ventanaGestionCafe.imgPromocional.setIcon(iconoAMostrar);
            ventanaGestionCafe.txtRutaImagen.setText(ruta);
        }
    }

    private void gestionarCafe() {
        Cafe cafe = comprobarDatos();
        if (cafe != null) {
            if (modificando) {
                modelo.modificarCafe(cafeAModificar, cafe.getNombre(), cafe.getImagenPromocional(),
                        cafe.getPorcentajeArabico(), cafe.getPorcentajeRobusta(), cafe.getLote());
            } else {
                modelo.anadirCafe(cafe);
            }
            ventanaGestionCafe.dispose();

        }
    }

    /**
     * Comprobación de todos los datos introducidos para la creación o modificación
     * de un café.
     *
     * @return Café generado a partir de los datos introducidos en la ventana.
     */
    private Cafe comprobarDatos() {
        Cafe cafe = null;
        if (comprobarDouble(ventanaGestionCafe.txtArabico.getText())
                && comprobarDouble(ventanaGestionCafe.txtRobusta.getText())) {
            if (ventanaGestionCafe.txtNombre.getText().replace(" ", "").length() != 0) {
                if (ventanaGestionCafe.cbLote.getSelectedItem() != null) {
                    if (!ventanaGestionCafe.txtRutaImagen.getText().equals("")) {
                        double arabico = Double.parseDouble(ventanaGestionCafe.txtArabico.getText());
                        double robusta = Double.parseDouble(ventanaGestionCafe.txtRobusta.getText());
                        if (arabico + robusta <= 100 && arabico + robusta >= 0) {
                            String nombre = ventanaGestionCafe.txtNombre.getText();
                            String rutaImagen = ventanaGestionCafe.txtRutaImagen.getText();
                            Lote lote = (Lote) ventanaGestionCafe.cbLote.getSelectedItem();
                            cafe = new Cafe(nombre, rutaImagen, arabico, robusta, lote);

                        } else {
                            Util.mostrarError(
                                    "La suma de los ingredientes no puede ser mayor al 100% ni menor al 0%.\n Ejemplo: Arábico: 45% Robusta: 30%.");
                        }
                    } else {
                        Util.mostrarError("Debes seleccionar una imagen promocional.");
                    }
                } else {
                    Util.mostrarError("Debes seleccionar un lote.");
                }
            } else {
                Util.mostrarError("El nombre no puede estar vacío.\n Ejemplo: Lavazza Café.");
            }
        } else {
            Util.mostrarError("Los valores de la mezcla no están en el formato correcto.\n Ejemplo: 23.2.");
        }
        return cafe;
    }

    /**
     * Comprobación para saber si un String es un Double.
     *
     * @param aComprobar String a comprobar.
     * @return Resultado de la comprobación.
     */
    private boolean comprobarDouble(String aComprobar) {
        boolean aDevolver = true;
        try {
            Double.parseDouble(aComprobar);
        } catch (NumberFormatException nfe) {
            aDevolver = false;
        }
        return aDevolver;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnSeleccionar":
                selecionarImagen();
                break;
            case "btnCancelar":
                ventanaGestionCafe.dispose();
                break;
            case "btnGestionar":
                gestionarCafe();
                break;
        }
    }
}
