package ajAntDron2k25.ajAntCiberDron;

public class ajHRastreadora extends ajHormiga{
    public ajHRastreadora() {
        super("ajHRastreadora");
    }

    @Override
    public boolean ajcomer(ajAlimento ajalimento) {
        return ajalimento instanceof ajHerbivoro;
    }
}
