package coffeetime.gui;

import coffeetime.base.Fabricante;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControladorGestionFabricantes implements ActionListener {

    private final GestionFabricantes ventanaGestionFabricantes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Fabricante fabricanteAModificar;

    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = false;
        fabricanteAModificar = null;

        initHandlers();
        ventanaGestionFabricantes.btnGestionar.setText("Añadir");
    }

    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo, Fabricante fabricante) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = true;
        fabricanteAModificar = fabricante;

        initHandlers();

        ventanaGestionFabricantes.txtNombre.setText(fabricante.getNombre());
        ventanaGestionFabricantes.txtDireccion.setText(fabricante.getDireccion());
        ventanaGestionFabricantes.txtTrabajadores.setText(String.valueOf(fabricante.getTrabajadores()));

        ventanaGestionFabricantes.dpFechaAlta.setDate(fabricante.getFechaAlta());

        if (fabricante.isInternacional()) {
            ventanaGestionFabricantes.chbxInternacional.setSelected(true);
        }

        ventanaGestionFabricantes.btnGestionar.setText("Modificar");

    }

    private void initHandlers() {
        ventanaGestionFabricantes.btnGestionar.addActionListener(this);
        ventanaGestionFabricantes.btnCancelar.addActionListener(this);
    }

    private void gestionarFabricante() {
        Fabricante fabricante = comprobarDatos();
        if (fabricante != null) {
            if (modificando) {
                modelo.modificarFabricante(fabricanteAModificar, fabricante.getNombre(),
                        fabricante.getDireccion(), fabricante.getTrabajadores(),
                        fabricante.getFechaAlta(), fabricante.isInternacional());
            } else {
                modelo.anadirFabricante(fabricante);
            }
            ventanaGestionFabricantes.dispose();

        }
    }

    private Fabricante comprobarDatos() {
        Fabricante fabricante = null;
        if (comprobarInt(ventanaGestionFabricantes.txtTrabajadores.getText())) {
            if (ventanaGestionFabricantes.txtNombre.getText().replace(" ", "").length() != 0) {
                if (ventanaGestionFabricantes.txtDireccion.getText().replace(" ", "").length() != 0) {
                    if (ventanaGestionFabricantes.dpFechaAlta.getDate() != null) {
                        String nombre = ventanaGestionFabricantes.txtNombre.getText();
                        String direccion = ventanaGestionFabricantes.txtDireccion.getText();
                        int trabajadores = Integer.parseInt(ventanaGestionFabricantes.txtTrabajadores.getText());
                        LocalDate fechaCreacion = ventanaGestionFabricantes.dpFechaAlta.getDate();
                        boolean internacional = false;
                        if (ventanaGestionFabricantes.chbxInternacional.isSelected()) {
                            internacional = true;
                        }
                        fabricante = new Fabricante(nombre, direccion, trabajadores, fechaCreacion, internacional);

                    } else {
                        Util.mostrarError("Debes seleccionar una fecha de alta.");
                    }
                } else {
                    Util.mostrarError(
                            "La dirección no puede estar vacía.\n Ejemplo: Avenida Ramón y Cajal, 5, 24002, León.");
                }
            } else {
                Util.mostrarError("El nombre no puede estar vacío.\n Ejemplo: Granell.");
            }

        } else {
            Util.mostrarError("El valor de los trabajadores no está en el formato correcto.\n Ejemplo: 25");
        }

        return fabricante;
    }

    private boolean comprobarInt(String aComprobar) {
        boolean aDevolver = true;
        try {
            Integer.parseInt(aComprobar);
        } catch (NumberFormatException nfe) {
            aDevolver = false;
        }
        return aDevolver;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnGestionar":
                gestionarFabricante();
                break;
            case "btnCancelar":
                ventanaGestionFabricantes.dispose();
                break;
        }
    }
}
