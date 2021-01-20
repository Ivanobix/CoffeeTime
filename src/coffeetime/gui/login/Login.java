package coffeetime.gui.login;

import javax.swing.*;

public class Login extends JDialog {
    JPasswordField txtContrasena;
    JTextField txtUsuario;
    JButton btnAceptar;
    JButton btnCancelar;
    private JPanel contentPane;


    public Login() {
        setContentPane(contentPane);
        setUndecorated(true);
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setModal(true);
    }

    private void initComponents() {
        contentPane.getRootPane().setDefaultButton(btnAceptar);
        txtUsuario.requestFocus();
    }
}
