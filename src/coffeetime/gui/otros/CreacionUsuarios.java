package coffeetime.gui.otros;

import javax.swing.*;

public class CreacionUsuarios extends JDialog {
    JButton btnAceptar;
    JButton btnCancelar;
    JPasswordField txtContrasena;
    JTextField txtUsuario;
    JRadioButton rbAdmin;
    JRadioButton rbNormal;
    JRadioButton rbInvitado;
    private JPanel contentPane;


    public CreacionUsuarios() {
        setContentPane(contentPane);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        txtUsuario.requestFocus();
    }


}
