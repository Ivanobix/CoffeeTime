package coffeetime.gui;

import coffeetime.base.Lote;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResumenLote extends JDialog {
    private JPanel contentPane;
    private JButton btnVerFabricante;
    private JLabel lblIdentificador;
    private JLabel lblUnidades;
    private JLabel lblCoste;
    private JLabel lblEnvasado;
    private JLabel lblCaducidad;


    public ResumenLote(Lote lote) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        lblIdentificador.setText(lote.getIdentificador());
        lblUnidades.setText(String.valueOf(lote.getNumeroUnidades()));
        lblCoste.setText(lote.getCosteTotal() + "€");
        lblEnvasado.setText(lote.getFechaDeEnvasado().toString());
        lblCaducidad.setText(lote.getFechaDeCaducidad().toString());

        btnVerFabricante.addActionListener(e -> new ResumenFabricante(lote.getFabricante()));
    }
}
