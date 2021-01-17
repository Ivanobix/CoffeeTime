package coffeetime.gui.otros;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;
import java.util.ArrayList;

public class AsignacionDeLotesAFabricante extends JDialog {
    private JPanel contentPane;
    private JList<Lote> listLotesDeOtrosFabricantes;
    private JButton btnCambiarFabricante;
    private DefaultListModel<Lote> dlm;

    private final Fabricante fabricante;
    private final ArrayList<Lote> lotes;


    public AsignacionDeLotesAFabricante(Fabricante fabricante, ArrayList<Lote> lotes) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        this.fabricante = fabricante;
        this.lotes = new ArrayList<>();
        this.lotes.addAll(lotes);
        initComponents();
        initHandlers();
        cargarDatos();

    }

    private void initComponents() {
        dlm = new DefaultListModel<Lote>();
        listLotesDeOtrosFabricantes.setModel(dlm);
        listLotesDeOtrosFabricantes.setCellRenderer(new Renderer(Renderer.LOTES));
    }

    private void initHandlers() {
        btnCambiarFabricante.addActionListener(e -> {
            if (!listLotesDeOtrosFabricantes.isSelectionEmpty()) {
                lotes.get(listLotesDeOtrosFabricantes.getSelectedIndex()).setFabricante(fabricante);
                actualizarLista();
            }

        });
    }

    private void cargarDatos() {
        lotes.removeIf(lote -> lote.getFabricante().equals(fabricante));
        for (Lote lote : lotes) {
            dlm.addElement(lote);
        }

    }

    private void actualizarLista() {
        dlm.clear();
        cargarDatos();
    }

}
