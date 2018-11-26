package estadosAtaque;

import Excepciones.ArmaNoCargada;

public class ArmaDescargada implements EstadosAtaque {
    @Override
    public void ataqueListo() throws ArmaNoCargada {
        throw new ArmaNoCargada();
    }

    @Override
    public void cargarArma()  {
    }

    @Override
    public void realizarAccion() {
    }
}