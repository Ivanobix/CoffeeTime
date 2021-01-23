package coffeetime.gui.otros;

import javax.swing.*;

public class PantallaDeCarga extends JDialog implements Runnable {
    private JPanel contentPane;
    private JProgressBar barraProgreso;

    public PantallaDeCarga() {
        setContentPane(contentPane);
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                barraProgreso.setValue(i);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        dispose();
    }
}
