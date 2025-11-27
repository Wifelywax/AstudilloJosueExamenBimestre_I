package ajAntDron2k25.ajAntCiberDron;

public abstract class ajAlimento {
String tipo;
    
    public ajAlimento(String tipo) { 
        this.tipo = tipo; 
    }
    
    @Override
    public String toString() { 
        return this.tipo; 
    }

}
