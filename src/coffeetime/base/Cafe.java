package coffeetime.base;

import java.io.Serializable;
import java.util.UUID;

/**
 * Café. Clase dedicada a la construcción de objetos tipo "Café" para su
 * posterior almacenamiento o tratamiento por diferentes clases.
 *
 * @author Iván García Prieto
 * @version 13.01.2021
 */
public class Cafe implements Serializable {

    private String identificador;
    private String nombre;
    private String imagenPromocional;
    private double porcentajeArabico;
    private double porcentajeRobusta;
    private Lote lote;

    /**
     * Constructor.
     *
     * @param nombre            Nombre del café.
     * @param imagenPromocional Fecha de envasado del café.
     * @param porcentajeArabico Porcentaje de café arábico en la mezcla.
     * @param porcentajeRobusta Porcentaje de café robusta en la mezcla.
     * @param lote              Lote al que pertenece el café.
     */
    public Cafe(String nombre, String imagenPromocional, double porcentajeArabico, double porcentajeRobusta,
                Lote lote) {
        super();
        identificador = UUID.randomUUID().toString().substring(0, 10);
        this.nombre = nombre;
        this.imagenPromocional = imagenPromocional;
        this.porcentajeArabico = porcentajeArabico;
        this.porcentajeRobusta = porcentajeRobusta;
        this.lote = lote;
    }

    /**
     * Devuelve el identificador del café.
     *
     * @return Identificador del café.
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Establece el identificador del café.
     *
     * @param identificador Identificador del café.
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * Devuelve el nombre del café.
     *
     * @return Nombre del café.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del café.
     *
     * @param nombre Nombre del café.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la ruta de la imagen promocional del café.
     *
     * @return Fecha de envasado del café.
     */
    public String getImagenPromocional() {
        return imagenPromocional;
    }

    /**
     * Establece la ruta de la imagen promocional del café.
     *
     * @param imagenPromocional Fecha de envasado del café.
     */
    public void setImagenPromocional(String imagenPromocional) {
        this.imagenPromocional = imagenPromocional;
    }

    /**
     * Devuelve el porcentaje de café arabico en la mezcla.
     *
     * @return Porcentaje de café tipo arabico en la mezcla.
     */
    public double getPorcentajeArabico() {
        return porcentajeArabico;
    }

    /**
     * Establece el porcentaje de café arabico en la mezcla.
     *
     * @param porcentajeArabico Porcentaje de café tipo arabico en la mezcla.
     */
    public void setPorcentajeArabico(double porcentajeArabico) {
        this.porcentajeArabico = porcentajeArabico;
    }

    /**
     * Devuelve el porcentaje de café robusta en la mezcla.
     *
     * @return Porcentaje de café tipo robusta en la mezcla.
     */
    public double getPorcentajeRobusta() {
        return porcentajeRobusta;
    }

    /**
     * Establece el porcentaje de café robusta en la mezcla.
     *
     * @param porcentajeRobusta Porcentaje de café tipo robusta en la mezcla.
     */
    public void setPorcentajeRobusta(double porcentajeRobusta) {
        this.porcentajeRobusta = porcentajeRobusta;
    }

    /**
     * Devuelve el lote del café.
     *
     * @return Lote del café.
     */
    public Lote getLote() {
        return lote;
    }

    /**
     * Establece el lote del café.
     *
     * @param lote Lote del café.
     */
    public void setLote(Lote lote) {
        this.lote = lote;
    }

    /**
     * Devuelve una cadena con la información básica de un café.
     */
    @Override
    public String toString() {
        return "Identificador: " + identificador + "   //   Nombre: " + nombre;
    }

}
