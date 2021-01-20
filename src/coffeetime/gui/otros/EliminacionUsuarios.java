package coffeetime.gui.otros;

import coffeetime.base.Usuario;

import javax.swing.*;

public class EliminacionUsuarios extends JDialog {
    JButton btnAceptar;
    JButton btnCancelar;
    JComboBox<Usuario> cbUsuario;
    private JPanel contentPane;


    public EliminacionUsuarios() {
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        btnAceptar.requestFocus();
    }

}
