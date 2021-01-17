package coffeetime.gui.otros;

import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.componentes.Renderer;

import javax.swing.*;
import java.util.ArrayList;

public class AsignacionDeLotesAFabricante extends JDialog {

    private JPanel contentPane;
    JList<Lote> listLotesDeOtrosFabricantes;
    JButton btnCambiarFabricante;
    DefaultListModel<Lote> dlm;

    public AsignacionDeLotesAFabricante() {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        dlm = new DefaultListModel<>();
        listLotesDeOtrosFabricantes.setModel(dlm);
        listLotesDeOtrosFabricantes.setCellRenderer(new Renderer(Renderer.LOTES));
    }

}
