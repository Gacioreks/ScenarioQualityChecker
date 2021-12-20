package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.Objects;

/**
 * Klasa StepCountVisitor jest implementacją interfejsu Visitor.
 * Służy do obliczania i zwracania liczby kroków w scenariuszu.
 */

public class StepCountVisitor implements Visitor{

    private int quantity = 0;

    /**
     * Funkcja visit uruchamiana jest dla każdego kroku scenariusza, zwiększa ona wartość prywatnego pola quantity o 1.
     * @param s Odwiedzany krok.
     */
    @Override
    public void visit(Step s) {
        if(!Objects.equals(s.value, "")) {
            this.quantity += 1;
        }
    }

    /**
     * Funkcja visit uruchamiana jest dla każdego scenariusza, jest pusta ponieważ wymaga tego interfejs który implementuje.
     * @param sc Odwiedzany podscenariusz.
     */
    @Override
    public void visit(SubScenario sc) {

    }

    /**
     * Funkcja getStepCount zwraca ilość kroków w scenariuszu oraz zeruje prywatne pole quantity.
     * Powinna być uruchamiana bezpośrednio po wywołaniu funkcji visit na wszystkich krokach scenariusza.
     * @return Ilość kroków w Scenariuszu.
     */
    public int getStepCount(){
        System.out.println("Liczba krokow: " + (this.quantity));
        int out = this.quantity;
        this.quantity = 0;
        return out;
    }
}

