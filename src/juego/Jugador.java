package juego;

import Excepciones.CasilleroOcupado;
import Excepciones.ExcedeLimiteDelMapa;
import espacio.Mapa;
import estructuras.Castillo;
import estructuras.Cuartel;
import estructuras.Estructura;
import estructuras.PlazaCentral;
import unidades.Aldeano;

import java.util.ArrayList;
import java.util.Iterator;

public class Jugador {
    private Mapa mapa;
    private Juego juego;
    private Castillo castillo;
    private ArrayList<PlazaCentral> plazasCentrales = new ArrayList<PlazaCentral>();
    private ArrayList<Cuartel> cuarteles = new ArrayList<Cuartel>();
    private ArrayList<Aldeano> aldeanos = new ArrayList<Aldeano>();
    private ArrayList<Aldeano> aldeanosOcupados = new ArrayList<Aldeano>();
    //private ArrayList<Aldeano> aldeanosOcupados = new ArrayList<Aldeano>();
    private int oro;
    //private int turnoNumero;
   // private Dictionary<String Estructuras[]> Necesito estructuras para ir actualizando sus turnos de construccion

    public Jugador(Mapa mapa, int posicionCastilloHorizontal , int posicionCastilloVertical , Juego juego) throws CasilleroOcupado, ExcedeLimiteDelMapa {
        castillo = new Castillo(this);
        plazasCentrales.add(new PlazaCentral(this));

        //this.juego = juego;
        //this.turnoNumero = 0;
        this.oro = 100;

        mapa.colocarEstructuraEn(castillo, posicionCastilloHorizontal, posicionCastilloVertical, 4);
        mapa.colocarEstructuraEn(plazasCentrales.get(0), posicionCastilloHorizontal - 2, posicionCastilloVertical, 2);

        for (int i = 0; i < 3; i++) {
            aldeanos.add(i, new Aldeano(this));
            mapa.colocarUnidadEn(aldeanos.get(i), posicionCastilloHorizontal - 3, posicionCastilloVertical + i);
        }
    }

    public void nuevoTurno() {
        //this.turnoNumero++;
        Iterator<Aldeano> iterador = aldeanos.iterator();

        while (iterador.hasNext()) {
            iterador.next().realizarAccionCorrespondiente();
        }
    }

    public void finalizarTurno() {
        juego.cambiarTurno();
    }


    public void sumarOro(int unidadesDeOro) {
        this.oro += unidadesDeOro;
    }

    public void construirPlazaCentral (Aldeano aldeano) { // Podria ser asi o que se le pase un indez de array
        PlazaCentral unaPlazaCentral = new PlazaCentral(this);

        plazasCentrales.add(unaPlazaCentral);
        aldeanosOcupados.add(aldeano);

        aldeano.comenzarConstruccion(unaPlazaCentral);
    }

    public void construirCuartel(Aldeano aldeano) {
        Cuartel unCuartel = new Cuartel();

        cuarteles.add(unCuartel);
        aldeanosOcupados.add(aldeano);

        aldeano.comenzarConstruccion(unCuartel);
    }

    public void repararEstructura(Aldeano unAldeano, Cuartel unCuartel) {
        aldeanosOcupados.add(unAldeano);

        unAldeano.comenzarReparacion(unCuartel);
    }

    public int getOro() {
        return this.oro;
    }
}
