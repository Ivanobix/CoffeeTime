package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.gui.otros.AsignacionDeLotesAFabricante;
import coffeetime.gui.otros.ControladorAsignacionDeLotesAFabricantes;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControladorGestionFabricantes implements ActionListener {

    private final GestionFabricantes ventanaGestionFabricantes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Fabricante fabricanteAModificar;

    private ResourceBundle idioma;

    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = false;
        fabricanteAModificar = null;
        idioma = Util.obtenerTraducciones();
        ventanaGestionFabricantes.dpFechaAlta.setDate(LocalDate.now());
        crearAtajos();
        initHandlers();
    }

    public ControladorGestionFabricantes(GestionFabricantes ventanaGestionFabricantes, Modelo modelo, Fabricante fabricante) {
        this.ventanaGestionFabricantes = ventanaGestionFabricantes;
        this.modelo = modelo;
        modificando = true;
        fabricanteAModificar = fabricante;
        crearAtajos();
        initHandlers();
        rellenarDatos();
    }

    private void initHandlers() {
        ventanaGestionFabricantes.btnGestionar.addActionListener(this);
        ventanaGestionFabricantes.btnCancelar.addActionListener(this);

        if (modificando) {
            ventanaGestionFabricantes.btnGestionarLotes.addActionListener(this);
        }
    }

    private void crearAtajos() {
        ventanaGestionFabricantes.btnGestionar.setMnemonic(KeyEvent.VK_1);
        ventanaGestionFabricantes.btnCancelar.setMnemonic(KeyEvent.VK_2);
        ventanaGestionFabricantes.btnGestionarLotes.setMnemonic(KeyEvent.VK_3);
    }

    private void rellenarDatos() {
        ventanaGestionFabricantes.txtNombre.setText(fabricanteAModificar.getNombre());
        ventanaGestionFabricantes.txtDireccion.setText(fabricanteAModificar.getDireccion());
        ventanaGestionFabricantes.txtTrabajadores.setText(String.valueOf(fabricanteAModificar.getTrabajadores()));

        ventanaGestionFabricantes.dpFechaAlta.setDate(fabricanteAModificar.getFechaAlta());

        if (fabricanteAModificar.isInternacional()) {
            ventanaGestionFabricantes.chbxInternacional.setSelected(true);
        }

        ventanaGestionFabricantes.lblLotesFabricados.setVisible(true);
        ventanaGestionFabricantes.btnGestionarLotes.setVisible(true);
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
                        int trabajadores = Integer.parseInt(ventanaGestionFabricantes.txtTrabajadores.getText());
                        if (trabajadores > 0 && trabajadores < 1500000) {
                            String nombre = ventanaGestionFabricantes.txtNombre.getText();
                            String direccion = ventanaGestionFabricantes.txtDireccion.getText();
                            LocalDate fechaCreacion = ventanaGestionFabricantes.dpFechaAlta.getDate();
                            boolean internacional = false;
                            if (ventanaGestionFabricantes.chbxInternacional.isSelected()) {
                                internacional = true;
                            }
                            fabricante = new Fabricante(nombre, direccion, trabajadores, fechaCreacion, internacional);
                        } else {
                            Util.mostrarError(idioma.getString("error.numTrabajadores"));
                        }


                    } else {
                        Util.mostrarError(idioma.getString("error.faltaFechaAlta"));
                    }
                } else {
                    Util.mostrarError(
                            idioma.getString("error.direccionVacia"));
                }
            } else {
                Util.mostrarError(idioma.getString("error.nombreVacioFabricante"));
            }

        } else {
            Util.mostrarError(idioma.getString("error.formatoTrabajadores"));
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
            case "btnGestionarLotes":
                new ControladorAsignacionDeLotesAFabricantes(new AsignacionDeLotesAFabricante(), fabricanteAModificar, modelo.getLotes());
                ventanaGestionFabricantes.dispose();
        }
    }
}
