package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

/**
 * Interfejs Visitor pozwala na implementacje wzorca visitor
 */
public interface Visitor {
    /**
     * Funkcja visit ktora powinna byc zaimplementowana dla kazdego visitora
     * @param s Odwiedzany krok
     */
    public void visit(Step s);
    /**
     * Funkcja visit ktora powinna byc zaimplementowana dla kazdego visitora
     * @param sc Odwiedzany podscenariusz
     */
    public void visit(SubScenario sc);
}
