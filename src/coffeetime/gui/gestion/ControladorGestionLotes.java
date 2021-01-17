package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorGestionLotes implements ActionListener {

    private final GestionLotes ventanaGestionLotes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Lote loteAModificar;

    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = false;
        loteAModificar = null;
        cargarFabricantes();
        initHandlers();

        ventanaGestionLotes.btnGestionar.setText("Añadir");

    }

    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo, Lote lote) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = true;
        loteAModificar = lote;
        cargarFabricantes();
        initHandlers();

        ventanaGestionLotes.txtCoste.setText(String.valueOf(lote.getCosteTotal()));
        ventanaGestionLotes.txtUnidades.setText(String.valueOf(lote.getNumeroUnidades()));
        ventanaGestionLotes.dpEnvasado.setDate(lote.getFechaDeEnvasado());
        ventanaGestionLotes.dpCaducidad.setDate(lote.getFechaDeCaducidad());

        ventanaGestionLotes.cbFabricante.setSelectedItem(lote.getFabricante());

        ventanaGestionLotes.btnGestionar.setText("Modificar");

    }

    private void initHandlers() {
        ventanaGestionLotes.btnGestionar.addActionListener(this);
        ventanaGestionLotes.btnCancelar.addActionListener(this);
    }

    private void cargarFabricantes() {
        ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
        for (Fabricante fabricante : fabricantes) {
            ventanaGestionLotes.dcbm.addElement(fabricante);
        }
    }

    private void gestionarLote() {
        Lote lote = comprobarDatos();
        if (lote != null) {
            if (modificando) {
                modelo.modificarLote(loteAModificar, lote.getNumeroUnidades(), lote.getCosteTotal(),
                        lote.getFechaDeEnvasado(), lote.getFechaDeCaducidad(), lote.getFabricante());
            } else {
                modelo.anadirLote(lote);
            }
            ventanaGestionLotes.dispose();

        }
    }

    private Lote comprobarDatos() {
        Lote lote = null;
        if (comprobarInt(ventanaGestionLotes.txtUnidades.getText())) {
            if (comprobarDouble(ventanaGestionLotes.txtCoste.getText())) {
                if (ventanaGestionLotes.cbFabricante.getSelectedItem() != null) {
                    if (ventanaGestionLotes.dpEnvasado.getDate() != null) {
                        if (ventanaGestionLotes.dpCaducidad.getDate() != null) {
                            double coste = Double.parseDouble(ventanaGestionLotes.txtCoste.getText());
                            if (coste > 0) {
                                int unidades = Integer.parseInt(ventanaGestionLotes.txtUnidades.getText());
                                if (unidades > 0) {
                                    LocalDate envasado = ventanaGestionLotes.dpEnvasado.getDate();
                                    LocalDate caducidad = ventanaGestionLotes.dpCaducidad.getDate();
                                    Fabricante fabricante = (Fabricante) ventanaGestionLotes.cbFabricante.getSelectedItem();
                                    lote = new Lote(unidades, coste, envasado, caducidad, fabricante);
                                } else {
                                    Util.mostrarError("Las unidades deben ser mayor que cero.\n Ejemplo: 500.");
                                }
                            } else {
                                Util.mostrarError("El coste debe ser mayor que cero.\n Ejemplo: 1.6.");
                            }
                        } else {
                            Util.mostrarError("Debes seleccionar una fecha de caducidad.");
                        }

                    } else {
                        Util.mostrarError("Debes seleccionar una fecha de envasado.");
                    }
                } else {
                    Util.mostrarError("Debes seleccionar un fabricante.");
                }
            } else {
                Util.mostrarError("El coste no es correcto.\n Ejemplo: 15.95.");
            }
        } else {
            Util.mostrarError("Las unidades no son correctas.\n Ejemplo: 10000");
        }
        return lote;
    }

    private boolean comprobarDouble(String aComprobar) {
        boolean aDevolver = true;
        try {
            Double.parseDouble(aComprobar);
        } catch (NumberFormatException nfe) {
            aDevolver = false;
        }
        return aDevolver;
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
                gestionarLote();
                break;
            case "btnCancelar":
                ventanaGestionLotes.dispose();
                break;
        }
    }
}
