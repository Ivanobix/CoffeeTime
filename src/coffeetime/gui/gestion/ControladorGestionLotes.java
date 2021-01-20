package coffeetime.gui.gestion;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorGestionLotes implements ActionListener {

    private final GestionLotes ventanaGestionLotes;
    private final Modelo modelo;
    private final boolean modificando;
    private final Lote loteAModificar;

    private ResourceBundle idioma;

    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = false;
        loteAModificar = null;
        idioma = Util.obtenerTraducciones();
        crearAtajos();
        initHandlers();
        cargarFabricantes();

        ventanaGestionLotes.dpCaducidad.setDate(LocalDate.now());
        ventanaGestionLotes.dpEnvasado.setDate(LocalDate.now());
    }

    public ControladorGestionLotes(GestionLotes ventanaGestionLotes, Modelo modelo, Lote lote) {
        this.ventanaGestionLotes = ventanaGestionLotes;
        this.modelo = modelo;
        modificando = true;
        loteAModificar = lote;
        crearAtajos();
        initHandlers();
        cargarFabricantes();
        rellenarDatos();
    }

    private void initHandlers() {
        ventanaGestionLotes.btnGestionar.addActionListener(this);
        ventanaGestionLotes.btnCancelar.addActionListener(this);
    }

    private void crearAtajos() {
        ventanaGestionLotes.btnGestionar.setMnemonic(KeyEvent.VK_1);
        ventanaGestionLotes.btnCancelar.setMnemonic(KeyEvent.VK_2);
    }

    private void rellenarDatos() {
        ventanaGestionLotes.txtCoste.setText(String.valueOf(loteAModificar.getCosteTotal()));
        ventanaGestionLotes.txtUnidades.setText(String.valueOf(loteAModificar.getNumeroUnidades()));
        ventanaGestionLotes.dpEnvasado.setDate(loteAModificar.getFechaDeEnvasado());
        ventanaGestionLotes.dpCaducidad.setDate(loteAModificar.getFechaDeCaducidad());

        ventanaGestionLotes.cbFabricante.setSelectedItem(loteAModificar.getFabricante());
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
                                    if (envasado.isBefore(caducidad)) {
                                        Fabricante fabricante = (Fabricante) ventanaGestionLotes.cbFabricante.getSelectedItem();
                                        lote = new Lote(unidades, coste, envasado, caducidad, fabricante);
                                    } else {
                                        Util.mostrarError(idioma.getString("error.caducidadAntesDeEnvasado"));
                                    }
                                } else {
                                    Util.mostrarError(idioma.getString("error.unidadesMenorQueCero"));
                                }
                            } else {
                                Util.mostrarError(idioma.getString("error.costeMenorQueCero"));
                            }
                        } else {
                            Util.mostrarError(idioma.getString("error.faltaCaducidad"));
                        }

                    } else {
                        Util.mostrarError(idioma.getString("error.faltaEnvasado"));
                    }
                } else {
                    Util.mostrarError(idioma.getString("error.fabricante"));
                }
            } else {
                Util.mostrarError(idioma.getString("error.coste"));
            }
        } else {
            Util.mostrarError(idioma.getString("error.unidades"));
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
