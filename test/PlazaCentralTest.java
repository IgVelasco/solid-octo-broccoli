import estructuras.PlazaCentral;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlazaCentralTest{

    @Test
    public void testPlazaCentralSeCreaCon450DeVida() {
        PlazaCentral plaza = new PlazaCentral();

        assertEquals(450, plaza.getVida());
    }
}
