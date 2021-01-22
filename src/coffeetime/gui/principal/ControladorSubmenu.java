package coffeetime.gui.principal;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.base.Usuario;
import coffeetime.gui.gestion.*;
import coffeetime.gui.visualizado.ResumenCafe;
import coffeetime.gui.visualizado.ResumenFabricante;
import coffeetime.gui.visualizado.ResumenLote;
import coffeetime.modelo.Modelo;
import coffeetime.util.Util;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControladorSubmenu implements ActionListener, KeyListener {

    private final Submenu submenu;
    private final Modelo modelo;
    private final ResourceBundle idioma;
    private int graficaFabricanteAMostrar;

    public ControladorSubmenu(Submenu submenu, Modelo modelo) {
        this.submenu = submenu;
        this.modelo = modelo;
        idioma = Util.obtenerTraducciones();
        graficaFabricanteAMostrar = 1;
        cargarDatos();
        initHandlers();
        crearAtajos();
        cargarUsuario();
        crearEstadisticas();
    }

    private void initHandlers() {
        submenu.btnAnadir.addActionListener(this);
        submenu.btnEliminar.addActionListener(this);
        submenu.btnModificar.addActionListener(this);
        submenu.btnMostrarInfoAdicional.addActionListener(this);
        submenu.btnCambiarGrafica.addActionListener(this);

        submenu.txtFiltro.addKeyListener(this);
    }

    private void crearAtajos() {
        submenu.btnAnadir.setMnemonic(KeyEvent.VK_1);
        submenu.btnEliminar.setMnemonic(KeyEvent.VK_2);
        submenu.btnModificar.setMnemonic(KeyEvent.VK_3);
        submenu.btnMostrarInfoAdicional.setMnemonic(KeyEvent.VK_4);
        submenu.btnCambiarGrafica.setMnemonic(KeyEvent.VK_5);
    }

    private void cargarDatos() {
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            ArrayList<Cafe> cafes = modelo.getCafes();
            for (Cafe cafe : cafes) {
                submenu.dlm.addElement(cafe);
            }
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            ArrayList<Lote> lotes = modelo.getLotes();
            for (Lote lote : lotes) {
                submenu.dlm.addElement(lote);
            }
        } else {
            ArrayList<Fabricante> fabricantes = modelo.getFabricantes();
            for (Fabricante fabricante : fabricantes) {
                submenu.dlm.addElement(fabricante);
            }
        }
    }

    private void crearEstadisticas() {
        submenu.pnEstadisticas.removeAll();
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoCafe(new DefaultPieDataset())));
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoLote(new DefaultCategoryDataset())));
        } else {
            submenu.pnEstadisticas.add(new ChartPanel(crearGraficoFabricante(new DefaultPieDataset())));
        }

        submenu.repaint();
    }

    private JFreeChart crearGraficoCafe(DefaultPieDataset dataset) {
        String tituloGrafica = idioma.getString("grafico.cafe");
        double arabico = 0;
        double robusta = 0;

        for (Cafe cafe : modelo.getCafes()) {
            arabico += cafe.getPorcentajeArabico();
            robusta += cafe.getPorcentajeRobusta();
        }
        dataset.setValue(idioma.getString("grafico.arabico"), arabico);
        dataset.setValue(idioma.getString("grafico.robusta"), robusta);

        return ChartFactory.createPieChart(tituloGrafica, dataset);
    }

    private JFreeChart crearGraficoLote(DefaultCategoryDataset dataset) {
        String tituloGrafica = idioma.getString("grafico.lote");
        for (Lote lote : modelo.getLotes()) {
            dataset.setValue(lote.getCosteTotal(), lote.getFabricante().getNombre(), lote.getIdentificador());
        }
        return ChartFactory.createBarChart(tituloGrafica, idioma.getString("general.lote"), idioma.getString("general.coste"), dataset);
    }

    private JFreeChart crearGraficoFabricante(DefaultPieDataset dataset) {
        String tituloGrafica;
        if (graficaFabricanteAMostrar == 1) {
            tituloGrafica = idioma.getString("grafico.fabricante1");
            int unidadesVendidas = 0;
            for (Fabricante fabricante : modelo.getFabricantes()) {
                for (Lote lote : modelo.getLotes()) {
                    if (lote.getFabricante().equals(fabricante)) {
                        unidadesVendidas += lote.getNumeroUnidades();
                    }
                }
                dataset.setValue(fabricante.getNombre(), unidadesVendidas);
                unidadesVendidas = 0;
            }
        } else {
            tituloGrafica = idioma.getString("grafico.fabricante2");
            ArrayList<String> variantesCafe = new ArrayList<>();
            for (Fabricante fabricante : modelo.getFabricantes()) {
                for (Lote lote : modelo.getLotes()) {
                    if (lote.getFabricante().equals(fabricante)) {
                        for (Cafe cafe : modelo.getCafes()) {
                            if (cafe.getLote().equals(lote) && !(variantesCafe.contains(cafe.getNombre()))) {
                                variantesCafe.add(cafe.getNombre());
                            }
                        }
                    }
                }
                dataset.setValue(fabricante.getNombre(), variantesCafe.size());
                variantesCafe.clear();
            }
        }

        return ChartFactory.createPieChart(tituloGrafica, dataset);
    }

    private void cambiarGraficaFabricante() {
        if (graficaFabricanteAMostrar == 1) {
            graficaFabricanteAMostrar = 2;
        } else {
            graficaFabricanteAMostrar = 1;
        }
        crearEstadisticas();
    }

    private void actualizarLista() {
        submenu.dlm.clear();
        cargarDatos();
    }

    private void anadir() {
        if (submenu.tipo == Submenu.TYPE_CAFES) {
            new ControladorGestionCafes(new GestionCafes(), modelo);
        } else if (submenu.tipo == Submenu.TYPE_LOTES) {
            new ControladorGestionLotes(new GestionLotes(), modelo);
        } else {
            new ControladorGestionFabricantes(new GestionFabricantes(), modelo);
        }

        submenu.dispose();
    }

    private void eliminar() {
        int seguroDeEliminar = Util.mostrarConfirmacion(idioma.getString("confirmacion.eliminarElemento"));
        if (seguroDeEliminar == JOptionPane.YES_OPTION) {
            Object objeto = submenu.listaElementos.getSelectedValue();
            if (objeto != null) {
                if (submenu.tipo == Submenu.TYPE_CAFES) {
                    modelo.eliminarCafe((Cafe) objeto);
                } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                    modelo.eliminarLote((Lote) objeto);
                } else {
                    modelo.eliminarFabricante((Fabricante) objeto);
                }
            } else {
                Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
            }

            actualizarLista();
        }

    }

    private void modificar() {
        Object objeto = submenu.listaElementos.getSelectedValue();
        if (objeto != null) {
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                new ControladorGestionCafes(new GestionCafes(), modelo, (Cafe) objeto);
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                new ControladorGestionLotes(new GestionLotes(), modelo, (Lote) objeto);
            } else {
                new ControladorGestionFabricantes(new GestionFabricantes(), modelo, (Fabricante) objeto);
            }
            submenu.dispose();
        } else {
            Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
        }

    }

    private void mostrarInfoAdicional() {
        Object objeto = submenu.listaElementos.getSelectedValue();
        if (objeto != null) {
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                new ResumenCafe((Cafe) objeto);
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                new ResumenLote((Lote) objeto);
            } else {
                new ResumenFabricante((Fabricante) objeto);
            }

        } else {
            Util.mostrarError(idioma.getString("error.nadaSeleccionado"));
        }
    }

    private void filtrar() {
        String filtro = submenu.txtFiltro.getText().trim().toLowerCase();
        if (!filtro.replaceAll(" ", "").equals("")) {
            submenu.dlm.clear();
            int filtrado = submenu.cbFiltrado.getSelectedIndex() + 1;
            if (submenu.tipo == Submenu.TYPE_CAFES) {
                buscarCafes(filtrado, filtro);
            } else if (submenu.tipo == Submenu.TYPE_LOTES) {
                buscarLotes(filtrado, filtro);
            } else {
                buscarFabricantes(filtrado, filtro);
            }

        } else {
            actualizarLista();
        }

    }

    private void buscarCafes(int filtrado, String filtro) {
        for (Cafe cafe : modelo.getCafes()) {
            switch (filtrado) {
                case 1: //Nombre
                    if (cafe.getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
                case 2: //Arabico
                    if (String.valueOf(cafe.getPorcentajeArabico()).contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
                case 3: //Robusta
                    if (String.valueOf(cafe.getPorcentajeRobusta()).contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
                case 4: //Lote
                    if (cafe.getLote().getIdentificador().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(cafe);
                    }
                    break;
            }
        }
    }

    private void buscarLotes(int filtrado, String filtro) {
        for (Lote lote : modelo.getLotes()) {
            switch (filtrado) {
                case 1: //Unidades
                    if (String.valueOf(lote.getNumeroUnidades()).contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;
                case 2: //Coste
                    if (String.valueOf(lote.getCosteTotal()).contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;
                case 3: //Fabricante
                    if (lote.getFabricante().getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(lote);
                    }
                    break;

            }
        }
    }

    private void buscarFabricantes(int filtrado, String filtro) {
        for (Fabricante fabricante : modelo.getFabricantes()) {
            switch (filtrado) {
                case 1: //Nombre
                    if (fabricante.getNombre().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
                case 2: //Dirección
                    if (fabricante.getDireccion().toLowerCase().contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
                case 3: //Trabajadores
                    if (String.valueOf(fabricante.getTrabajadores()).contains(filtro)) {
                        submenu.dlm.addElement(fabricante);
                    }
                    break;
            }
        }
    }

    private void cargarUsuario() {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("data/account.conf"));
            String nivelUsuario = properties.getProperty("NivelUsuario");
            activarFunciones(nivelUsuario);
        } catch (Exception e) {
            activarFunciones(String.valueOf(Usuario.ADMIN));
        }
    }

    private void activarFunciones(String nivelUsuario) {
        if (nivelUsuario.equals(String.valueOf(Usuario.DEFAULT))) {
            submenu.btnModificar.setEnabled(false);
            submenu.btnEliminar.setEnabled(false);
        } else if (nivelUsuario.equals(String.valueOf(Usuario.BASICO))) {
            submenu.btnAnadir.setEnabled(false);
            submenu.btnEliminar.setEnabled(false);
            submenu.btnModificar.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnAnadir":
                anadir();
                break;
            case "btnEliminar":
                eliminar();
                break;
            case "btnModificar":
                modificar();
                break;
            case "btnMostrarInfoAdicional":
                mostrarInfoAdicional();
                break;
            case "btnCambiarGrafica":
                cambiarGraficaFabricante();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        filtrar();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

}
