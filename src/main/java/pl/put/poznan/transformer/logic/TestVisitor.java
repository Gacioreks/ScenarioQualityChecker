package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.base.Scenario;
import pl.put.poznan.transformer.base.Step;

public class TestVisitor implements Visitor{

    @Override
    public void visit(Step s) {
        System.out.println(s.content + " liczba podkrokow w kroku:" + s.steps.size());
        //System.out.println("I'm in the step!");
    }

    @Override
    public void visit(Scenario sc) {
        System.out.println(sc.title + " Liczba kroków głównych w scenariuszu:"+ sc.steps.size());
        //System.out.println("I'm in the scenario!");

    }
}
