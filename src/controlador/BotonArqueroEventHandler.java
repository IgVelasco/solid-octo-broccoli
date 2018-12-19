package controlador;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import modelo.espacio.Casillero;
import modelo.juego.Juego;
import modelo.unidades.Arquero;

public class BotonArqueroEventHandler extends BotonEventHandler {
    Button boton;
    Arquero arquero;

    public BotonArqueroEventHandler(Casillero unCasillero, Button unBoton, Juego unJuego) {
        arquero = (Arquero) unCasillero.getContenido();
        boton = unBoton;
        ContextMenu contextMenu = new ContextMenu();

        MenuItem mover = new MenuItem("Mover");
        MenuItem atacar = new MenuItem("Atacar");
        MenuItem informacion = new MenuItem("Informacion");

        informacion.setOnAction(new MostrarInformacionUnidadHandler(this.arquero));

        mover.setOnAction(new MoverHandler(this.arquero, unJuego));
        atacar.setOnAction(new AtacarHandler(this.arquero, unJuego));

        contextMenu.getItems().addAll(mover, atacar, informacion);
        boton.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(boton, event.getScreenX(), event.getScreenY());
            }
        });
    }
}
