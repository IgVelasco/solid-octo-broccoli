package controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import modelo.espacio.Contenible;
import modelo.espacio.Posicion;
import modelo.estructuras.Estructura;
import modelo.excepciones.AldeanoOcupado;
import modelo.excepciones.ContenibleNoPropia;
import modelo.excepciones.EdificioConVidaMaxima;
import modelo.excepciones.EstructuraYaEnReparacion;
import modelo.juego.Juego;
import modelo.unidades.Aldeano;
import vista.JuegoVista;
import vista.MapaView;

public class RepararHandler extends AccionSobreCasilla implements EventHandler<ActionEvent> {
    private Aldeano aldeano;
    Juego juego;

    RepararHandler(Aldeano unAldeano, Juego unJuego) {
        aldeano = unAldeano;
        juego = unJuego;
    }

    @Override
    public void handle(ActionEvent event) {
        MapaView mapaView = MapaView.getInstancia();
        mapaView.setAccionSobreCasilla(this);
    }

    public void realizarAccion(MapaView mapaView, Posicion posicion) {
        Contenible unContenible = mapaView.getMapa().getContenido(posicion.getPosX(), posicion.getPosY());
        try {
            juego.getJugadorActual().repararEstructura(aldeano, (Estructura) unContenible);

            String rutaSonido = "/vista/sonidos/reparar.mp3";
            AudioClip sonidoReparar = new AudioClip(
                    BotonEventHandler.class.getResource(rutaSonido).toExternalForm()
            );

            sonidoReparar.play();

        } catch (AldeanoOcupado e) {
            alertar("¡Aldeano ocupado!");
        } catch (EdificioConVidaMaxima e) {
            alertar("¡Vida máxima alcanzada!");
        } catch (ContenibleNoPropia e){
            alertar("No te pertenece!");
        } catch (EstructuraYaEnReparacion e) {
            alertar("¡El edificio ya se está reperando");
        }

        JuegoVista juegoVista = JuegoVista.getInstancia();
        juegoVista.actualizar(mapaView.getJuego());
    }
}
