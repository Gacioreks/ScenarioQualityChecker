package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

/**
 * Interfejs Visitor pozwala na implementację wzorca visitor
 */
public interface Visitor {
    /**
     * Funkcja visit która powinna być zaimplementowana dla każdego visitors
     * @param s Odwiedzany krok
     */
    public void visit(Step s);
    /**
     * Funkcja visit która powinna być zaimplementowana dla każdego visitors
     * @param sc Odwiedzany pod scenariusz
     */
    public void visit(SubScenario sc);
}
