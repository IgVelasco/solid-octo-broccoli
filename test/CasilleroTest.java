import modelo.espacio.Casillero;
import modelo.espacio.Contenible;
import modelo.excepciones.CasilleroOcupado;
import modelo.unidades.Aldeano;
import org.junit.Test;

import static org.junit.Assert.*;


public class CasilleroTest {

    @Test
    public void testCasilleroSeCreaSinContenido() {
        Casillero unCasillero = new Casillero(null);
        assertFalse(unCasillero.casilleroEstaOcupado());


    }

    @Test
    public void testCasilleroPuedeContenerUnaUnidadContenible() {
        Contenible unAldeano = new Aldeano(null,null);
        Casillero unCasillero = new Casillero(null);

        unCasillero.contener(unAldeano);

        assertTrue(unCasillero.casilleroEstaOcupado());
        assertEquals(unAldeano, unCasillero.getContenido());
    }

    @Test
    public void testCasilleroQuedaLibreLuegoDeLiberarlo() {
        Contenible unAldeano = new Aldeano(null,null);
        Casillero unCasillero = new Casillero(null);

        unCasillero.contener(unAldeano);

        unCasillero.liberar();

        assertFalse(unCasillero.casilleroEstaOcupado());
        assertNull(unCasillero.getContenido());
    }

    @Test(expected = CasilleroOcupado.class)
    public void testCasilleroLanzaExcepcionSiSeIntentaContenerMientrasEstaOcupado() {
        Contenible unAldeano = new Aldeano(null, null);
        Casillero unCasillero = new Casillero(null);
        unCasillero.contener(unAldeano);

        Contenible otroAldeano = new Aldeano(null, null);
        unCasillero.contener(otroAldeano);

    }
}
