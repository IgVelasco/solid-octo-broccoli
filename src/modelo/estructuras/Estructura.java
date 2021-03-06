package modelo.estructuras;

import modelo.espacio.Contenible;
import modelo.espacio.Posicion;
import modelo.excepciones.EdificioConVidaMaxima;
import modelo.excepciones.EstructuraYaEnReparacion;
import modelo.juego.Jugador;
import modelo.unidades.Aldeano;

import java.util.LinkedList;

import static java.lang.Integer.min;

public abstract class Estructura implements Contenible {

    private static final int
            DANO_ARQUERO = 10,
            DANO_ESPADACHIN = 15,
            DANO_CASTILLO = 20,
            DANO_ASEDIO = 75;
    int vida;
    int vidaMaxima;
    int velocidadDeReparacion;
    Jugador propietario;
    LinkedList<Posicion> posiciones;
    private Aldeano obrero;


    public void reparar(Aldeano unAldeano) {
        if (vida + velocidadDeReparacion > vidaMaxima) {
            vida = vidaMaxima;
            unAldeano.liberarAldeano();
            return;
        }
        vida += velocidadDeReparacion;
    }

    public boolean sonDelMismoJugador(Jugador unPropietario) {
        return (unPropietario == this.propietario);
    }

    public int getVida() {
        return vida;
    }

    public void recibirAtaque(int dano) {
        this.vida -= dano;
        if (this.vida <= 0)
            this.borrarEstructura();
    }

    public void ataqueDeEspadachin() {
        recibirAtaque(DANO_ESPADACHIN);
    }

    public void ataqueDeArquero() {
        recibirAtaque(DANO_ARQUERO);

    }

    public void ataqueDeCastillo() {
        recibirAtaque(DANO_CASTILLO);
    }

    public void ataqueDeAsedio() {
        recibirAtaque(DANO_ASEDIO);
    }

    public void borrarEstructura() {
        propietario.borrarEstructura(posiciones);
    }



    public void ponerAReparar(Aldeano unAldeano) {
        if ((obrero != unAldeano) && (obrero != null)) {
            unAldeano.liberarAldeano();
            throw new EstructuraYaEnReparacion();
        }
        obrero = unAldeano;
        if (vida == vidaMaxima)
            throw new EdificioConVidaMaxima();
    }

    public int calcularDistancia(int x, int y) {
        int distanciaMinima = Integer.MAX_VALUE;
        for (Posicion posicion : posiciones) {
            distanciaMinima = min(distanciaMinima, posicion.distancia(x, y));
        }
        return distanciaMinima;

    }

    public void agregarPosicion(Posicion posicion) {
        posiciones.add(posicion);
    }


    public Jugador getPropietario(){ return propietario;};

    public LinkedList<Posicion> getPosiciones(){
        return posiciones;
    };
}

