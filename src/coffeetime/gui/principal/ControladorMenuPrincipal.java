package coffeetime.gui.principal;

import coffeetime.modelo.Modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorMenuPrincipal implements ActionListener {

    private final MenuPrincipal menuPrincipal;
    private final Modelo modelo;

    public ControladorMenuPrincipal(MenuPrincipal menuPrincipal, Modelo modelo) {
        this.menuPrincipal = menuPrincipal;
        this.modelo = modelo;
        initHandlers();
    }

    private void initHandlers() {
        menuPrincipal.btnCafes.addActionListener(this);
        menuPrincipal.btnConfiguracion.addActionListener(this);
        menuPrincipal.btnFabricantes.addActionListener(this);
        menuPrincipal.btnLotes.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnCafes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_CAFES), modelo);
                break;
            case "btnFabricantes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_FABRICANTES), modelo);
                break;
            case "btnLotes":
                new ControladorSubmenu(new Submenu(Submenu.TYPE_LOTES), modelo);
                break;

            case "btnConfiguracion":

                break;
            case "mnitGuardar":

                break;
            case "mnitCargar":

                break;
            case "mnitNuevo":

                break;
            case "mnitDeshacer":

                break;
        }
    }
}
