package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Step;
import pl.put.poznan.transformer.base.SubScenario;


/**
 * Klasa ShowScenarioVisitor jest implementacja interfejsu Visitor.
 * Sluzy do wyswietlania scenariusza.
 */

public class ShowScenarioVisitor implements Visitor{


    /**
     * Funkcja visit uruchamiana jest dla kazdego scenariusza, jest pusta poniewaz wymaga tego interfejs ktory implementuje.
     * @param sc Odwiedzany podscenariusz.
     */
    @Override
    public void visit(SubScenario sc) {

    }


    /**
     * Funkcja visit uruchamiana jest dla kazdego kroku scenariusza, wyswietla krok.
     * @param s Odwiedzany krok.
     */
    @Override
    public void visit(Step s) {
        StringBuilder tmp = new StringBuilder();
        for(int i=0;i<s.level;i++) tmp.append("\t");

        System.out.println(tmp+s.value);

    }
}
