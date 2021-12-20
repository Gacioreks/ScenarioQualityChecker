package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;

import java.util.Objects;

/**
 * Klasa StepCountVisitor jest implementacja interfejsu Visitor.
 * Sluzy do obliczania i zwracania liczby krokow w scenariuszu.
 */

public class StepCountVisitor implements Visitor{

    private int quantity = 0;

    /**
     * Funkcja visit uruchamiana jest dla kazdego kroku scenariusza, zwieksza ona wartosc prywatnego pola quantity o 1.
     * @param s Odwiedzany krok.
     */
    @Override
    public void visit(Step s) {
        if(!Objects.equals(s.value, "")) {
            this.quantity += 1;
        }
    }

    /**
     * Funkcja visit uruchamiana jest dla kazdego scenariusza, jest pusta poniewaz wymaga tego interfejs ktory implementuje.
     * @param sc Odwiedzany podscenariusz.
     */
    @Override
    public void visit(SubScenario sc) {

    }

    /**
     * Funkcja getStepCount zwraca ilosc krokow w scenariuszu oraz zeruje prywatne pole quantity.
     * Powinna być uruchamiana bezposrednio po wywolaniu funkcji visit na wszystkich krokach scenariusza.
     * @return Ilosc krokow w Scenariuszu.
     */
    public int getStepCount(){
        System.out.println("Liczba krokow: " + (this.quantity));
        int out = this.quantity;
        this.quantity = 0;
        return out;
    }
}
