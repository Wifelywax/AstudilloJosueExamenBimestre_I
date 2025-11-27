package ajAntDron2k25.ajAntCiberDron;

import java.util.regex.*;

public class ajAntCiberDron implements IajIA {
    private ajHormiga piloto;
    
    
    private String patronBomba = "(ab*)|(abcd(t)+)";

    public ajAntCiberDron(ajHormiga piloto) {
        this.piloto = piloto;
    }

    public String getTipoPiloto() {
        return piloto.tipo;
    }
//Refactorizacion
    public void verificarVida(ajAlimento comida) {
        if (piloto.ajcomer(comida)) {
            System.out.println(">> Hormiga " + piloto.tipo + " comió " + comida + " y VIVE.");
        } else {
            System.out.println(">> Hormiga " + piloto.tipo + " comió " + comida + " y MUERE.");
        }
    }

    @Override
    public boolean ajbuscar(String tipoArsenal) {
    
        Pattern p = Pattern.compile(patronBomba);
        Matcher m = p.matcher(tipoArsenal);
        return m.matches(); 
    }

}
