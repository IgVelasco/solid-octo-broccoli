package espacio;

import Excepciones.CasilleroOcupado;
import Excepciones.ExcedeLimiteDelMapa;
import contenibles.Contenible;
import unidades.UnidadMovil;

public class Mapa {

    private int cantCeldas;
    private Casillero[][] mapa;

    public Mapa(int x, int y) {
        mapa = new Casillero[x][y];

        cantCeldas = x*y;

        for (int i = 0 ; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Casillero casillero = new Casillero();
                mapa[i][j] = casillero;
            }
        }
    }

    public int getCantCeldas() { return this.cantCeldas;}

    public Contenible getContenido (int x, int y) throws ExcedeLimiteDelMapa {
        try {
            return this.mapa[x][y].getContenido();
        } catch (IndexOutOfBoundsException errorDeLimites) {
            throw new ExcedeLimiteDelMapa();
        }
    }

    public void colocarUnidadEn(Contenible unidad, int x, int y) throws CasilleroOcupado, ExcedeLimiteDelMapa {
        try {
            this.mapa[x][y].contener(unidad); // Podriamos hacer que casillero esta ocupado tire la excepcion
        } catch (IndexOutOfBoundsException errorDeLimites) {
            throw new ExcedeLimiteDelMapa();
        }
    }


    public void colocarEstructuraEn(Contenible unidad, int x, int y, int dimension) throws CasilleroOcupado, ExcedeLimiteDelMapa {
        if(casillerosEstanOcupados(x, y, dimension))
            throw new CasilleroOcupado();
        for(int i = 0; i < dimension ; i++){
            for(int j = 0; j < dimension ; j++){
                this.mapa[x + i][y + j].contener(unidad);
            }
        }
    }

    public boolean casillerosEstanOcupados(int x, int y, int dimensiones) throws ExcedeLimiteDelMapa {
        for(int i = 0; i < dimensiones ; i++){
            for(int j = 0; j < dimensiones ; j++){
               try{
                  if (this.mapa[x + i][y + j].casilleroEstaOcupado()){
                      return true;
                  }
               }catch (IndexOutOfBoundsException errorDeLimites){
                   throw new ExcedeLimiteDelMapa();
               }
            }
        }
        return false;
    }


    public void liberarUbicacion(int x, int y) {
        this.mapa[x][y].liberar();
    }

    // TODO ESTO ME PARECE MAL POR QUE YO LE DIGO AL MAPA QUE ME MUEVA LAS COSAS NO A LA UNIDAD MOVIBLE, MOVETE.

    public void mover(int x, int y, int incX, int incY) throws CasilleroOcupado, ExcedeLimiteDelMapa {
        UnidadMovil unidad = (UnidadMovil) this.getContenido( x, y); // aca hay que lanzar error si es estructura.
            this.colocarUnidadEn(unidad, x + incX, y + incY);
        this.liberarUbicacion( x, y);
    }

}


