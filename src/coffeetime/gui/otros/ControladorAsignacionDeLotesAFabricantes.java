package coffeetime.gui.otros;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;

import java.util.ArrayList;

public class ControladorAsignacionDeLotesAFabricantes {

    private final AsignacionDeLotesAFabricante ventanaAsignacionDeLotesAFabricante;
    private final Fabricante fabricante;
    private final ArrayList<Lote> lotes;

    public ControladorAsignacionDeLotesAFabricantes(AsignacionDeLotesAFabricante ventanaAsignacionDeLotesAFabricante, Fabricante fabricante, ArrayList<Lote> lotes) {
        this.fabricante = fabricante;
        this.ventanaAsignacionDeLotesAFabricante = ventanaAsignacionDeLotesAFabricante;
        this.lotes = new ArrayList<>();
        this.lotes.addAll(lotes);

        initHandlers();
        cargarDatos();
    }

    private void initHandlers() {
        ventanaAsignacionDeLotesAFabricante.btnCambiarFabricante.addActionListener(e -> {
            if (!ventanaAsignacionDeLotesAFabricante.listLotesDeOtrosFabricantes.isSelectionEmpty()) {
                lotes.get(ventanaAsignacionDeLotesAFabricante.listLotesDeOtrosFabricantes.getSelectedIndex()).setFabricante(fabricante);
                actualizarLista();
            }

        });
    }

    private void cargarDatos() {
        lotes.removeIf(lote -> lote.getFabricante().equals(fabricante));
        for (Lote lote : lotes) {
            ventanaAsignacionDeLotesAFabricante.dlm.addElement(lote);
        }

    }

    private void actualizarLista() {
        ventanaAsignacionDeLotesAFabricante.dlm.clear();
        cargarDatos();
    }
}
