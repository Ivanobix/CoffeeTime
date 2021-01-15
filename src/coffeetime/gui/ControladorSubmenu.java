package coffeetime.gui;

import coffeetime.modelo.Modelo;

public class ControladorSubmenu {

    private final Submenu submenu;
    private final Modelo modelo;

    public ControladorSubmenu(Submenu submenu, Modelo modelo) {
        this.submenu = submenu;
        this.modelo = modelo;
        initHandlers();
    }

    private void initHandlers() {

    }
}
