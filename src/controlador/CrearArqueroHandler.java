package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.espacio.Posicion;
import modelo.estructuras.Cuartel;
import modelo.juego.Juego;
import modelo.unidades.Arquero;
import vista.JuegoVista;
import vista.MapaView;

public class CrearArqueroHandler implements EventHandler<ActionEvent>, AccionSobreCasilla {
    Cuartel cuartel;
    Juego juego;

    public CrearArqueroHandler(Cuartel unCuartel, Juego unJuego) {
        cuartel = unCuartel;
        juego = unJuego;
    }

    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.setAccionSobreCasilla(this);
    }

    public void realizarAccion(MapaView mapaView) {
        Posicion destino = mapaView.getDestino();
        System.out.println(destino.getPosX());
        Arquero unArquero = cuartel.crearArquero(cuartel.getPropietario().getOro(), juego.getJugadorActual());
        mapaView.getMapa().colocarUnidadEn(unArquero, destino.getPosX(), destino.getPosY());
        JuegoVista juegoVista = JuegoVista.getInstancia();
        juegoVista.actualizar(mapaView.getJuego());
    }
}
