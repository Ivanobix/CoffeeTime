package coffeetime.gui;

import coffeetime.base.Cafe;
import coffeetime.base.Fabricante;
import coffeetime.base.Lote;
import coffeetime.modelo.Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorSubmenu implements ActionListener {

    private final Submenu submenu;
    private final Modelo modelo;

    public ControladorSubmenu(Submenu submenu, Modelo modelo) {
        this.submenu = submenu;
        this.modelo = modelo;
        initHandlers();
        cargarDatos();
    }

    private void initHandlers() {
        submenu.btnAnadir.addActionListener(this);
        submenu.btnEliminar.addActionListener(this);
        submenu.btnModificar.addActionListener(this);
        submenu.btnMostrarInfoAdicional.addActionListener(this);

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
            //Mostrar aviso de ningún objeto ha sido seleccionado
        }

        actualizarLista();
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
            //Mostrar aviso de nigún objeto ha sido seleccionado
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
            //Mostrar aviso de nigún objeto ha sido seleccionado
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
        }
    }
}
