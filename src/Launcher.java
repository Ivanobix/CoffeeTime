import coffeetime.gui.ControladorMenuPrincipal;
import coffeetime.gui.MenuPrincipal;
import coffeetime.modelo.Modelo;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

/**
 * Coffee Time. Aplicación diseñada para la gestión y relación de cafés, lotes y
 * sus fabricantes. Permite la insercción, eliminación, modificación, relación y
 * visualización de dichos elementos. Para una mayor productividad permite a su
 * vez guardar y cargar elementos, así como modificar el aspecto visual de la
 * interfaz.
 *
 * @author Iván García Prieto
 * @version 14.01.2021
 */
public class Launcher {

    /**
     * Constructor. Inicializa la aplicación. Tras un inicio de sesión satisfactorio
     * muestra la ventana principal desde la cual se accede a todas las
     * funcionalidades de la aplicación.
     *
     * @param args Argumentos de inicialización.
     */
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new ControladorMenuPrincipal(new MenuPrincipal(), new Modelo());
    }
}
