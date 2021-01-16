package coffeetime.util;

import javax.swing.*;
import java.awt.*;

/**
 * Util. Clase dedicada a la creación de JOptionPanels personalizados para su
 * uso en la aplicación principal, permitiendo así una mayor accesibilidad y la
 * simplificación del código.
 *
 * @author Iván García Prieto
 * @version 13.01.2021
 */
public class Util {

    public static final int ACEPTAR = JOptionPane.OK_OPTION;
    public static final int CANCELAR = JOptionPane.CANCEL_OPTION;

    /**
     * Mensaje de Error. Muestra por pantalla una ventana indicando un error
     * determinado.
     *
     * @param error Mensaje de error a mostrar al usuario.
     */
    public static void mostrarError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Confirmación. Muestra por pantalla una ventana que permite al usuario escoger
     * entre dos posibles acciones.
     *
     * @param mensaje Mensaje de confirmación a mostrar al ususario.
     * @return Mensaje de confirmación generado.
     */
    public static int mostrarConfirmacion(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, "Confirmación", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Escalar imagen. Redimensiona la imagen proporcionada para concidir con los
     * valores proporcionados, pero manteniendo la relación de aspecto.
     *
     * @param iconoOriginal Imagen inicial.
     * @param altoDeseado   Alto deseado.
     * @param anchoDeseado  Ancho deseado.
     * @return Icono redimensionado generado.
     */
    public static ImageIcon escalarImagen(ImageIcon iconoOriginal, int altoDeseado, int anchoDeseado) {
        Image imagenOriginal = iconoOriginal.getImage();

        int altoOriginal = iconoOriginal.getIconHeight();
        int anchoOriginal = iconoOriginal.getIconWidth();

        double relacionAncho = (double) anchoDeseado / anchoOriginal;
        double relacionAlto = (double) altoDeseado / altoOriginal;

        double relacion = Math.min(relacionAlto, relacionAncho);

        int anchoRecomendado = (int) (anchoOriginal * relacion);
        int altoRecomendado = (int) (altoOriginal * relacion);

        return new ImageIcon(imagenOriginal.getScaledInstance(anchoRecomendado, altoRecomendado, Image.SCALE_SMOOTH));
    }
}
